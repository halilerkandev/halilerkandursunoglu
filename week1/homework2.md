# Ödev 2

## Kodlama

- [**Collection Pipeline** by ***Martin Fowler***](https://martinfowler.com/articles/collection-pipeline/)
- Beklenenler (yukarıda yer alan yazıdan)
    - Ana başlıkların birkaç cümleyle özetinin çıkarılması
    - Spring initializr ile bir proje oluşturulması; Lombok ve spring-boot-starter-test dependency’ lerinin eklenmesi
    - Yazıda yer alan operatörlerin Java’daki karşılıklarının bulunup her biri için birer örnek metot yazılması (bazı operatörlerin birebir karşılığı olmayabilir, küçük dokunuşlar yapmak durumunda kalabilirsiniz)
    - (Bonus) Metotlara Junit ile test yazılması

---

## Ana Başlıkların Özetleri

### First encounters

Collection Pipeline Pattern ile Unix kullanmışsanız karşılaşmışsınızdır.

```
grep -l 'nosql' bliki/entries/* | xargs wc -w | sort -nr | head -4 | tail -3
```

Diğer command line environment'lar ile karşılaştırınca bu gerçekten çok güçlü kalıyor.

Daha önce Smalltalk kullandıysanız aynı pattern'ı select, sortBy, copyFrom gibi lambda methodlar kullanarak
uygulamışsınızdır.

```
((someArticles 
      select: [ :each | each tags includes: #nosql])
      sortBy: [:a :b | a words > b words]) 
      copyFrom: 1 to: 3
```

Clojure için örnek yazmak gerekirse:

```
(->> (articles)
     (filter #(some #{:nosql} (:tags %)))
     (sort-by :words >)
     (take 3))
```

Fonksiyonel programlama dillerinde Smaltalk'un lambda method'larını kullanmaya aşinayız. Nesne Yönelimli programlama
dilleri bu kullanıma adapte olamadılar ve bu yüzden Collection Pipeline Pattern'ı destekleyen zengin collection
method'larına sahip olamadılar. Java bu pattern'ı destekleyici adımlar atsa da bir Nesne Yönelimli Programcı için
bir tek method'lu bir class olmuş oldu.

### Defining Collection Pipeline

Collection Pipeline'ı yazılımı nasıl modülerleştirebileceğimizin ve oluşturabileceğimizin bir modeli olarak görebiliriz.
Çoğu pattern gibi, birçok yerde ortaya çıkıyor, ancak sadece yüzeysel olarak farklı görünüyor. Ancak altta yatan modeli
anladığınızda yeni bir ortamda ne yapmak istediğinizi anlamanızı ve ortaya çıkarmanızı kolaylaştırır.

Bir Collection Pipeline aralarında item'lar içeren bir collection transferi gerçekleştiren bir dizi işlem düzenler. 
Tek bir value emit eden son işlem hariç her işlem bir girdi olarak bir koleksiyon alır ve başka bir koleksiyon emit 
eder. Individual Operation'lar basittir, ancak çeşitli işlemleri bir araya getirerek karmaşık davranışlar
yaratabilirsiniz.

### Exploring more pipelines and operations

### Alternatives

Collection Pipeline Pattern, şimdiye kadar bahsedilen türden şeyleri başarmanın tek yolu değil. En belirgin alternatif,
çoğu insanın bu durumlarda genellikle kullanacağı şey döngüleri kullanmaktır.

Collection Pipeline:

```
some_articles
  .select{|a| a.tags.include?(:nosql)}
  .sort_by{|a| a.words}
  .take(3)
```

Loop:

```
result = []
some_articles.each do |a|
  result << a if a.tags.include?(:nosql)   
end
result.sort_by!(&:words)
return result[0..2]
```

Coffeescript'te olduğu gibi bazı diller yapı olarak comprehension'ları sıklıkla kullanırlar. Bin kelimeden uzun olan 
tüm makalelerin başlıklarını almak için şu örnekleri verebiliriz:

Pipeline:

```
some_articles
  .filter (a) ->
    a.words > 1000
  .map (a) ->
    a.title
```

Comprehension:

```
(a.title for a in some_articles when a.words > 1000)
```

### Nested Operator Expressions

Collection'lar ile yapabileceğiniz yararlı şeylerden biri, onları Set Operation'larla manipüle edebilmektir.

Set Operation'larla ilgili akılda tutulması gereken bir diğer nokta, Collection'ların genellikle sıralı ve kopyalara
izin veren listeler olmasıdır. Set Operation'ların ne anlama geldiğini görmek için library'nizin özelliklerine bakmanız
gerekir. Clojure, Set Operation'larını kullanmadan önce listelerinizi setlere dönüştürmeye zorlar. Ruby, Set 
Operation'larına herhangi bir diziyi kabul eder, ancak girdi sırasını korurken çıktısındaki kopyaları kaldırır.

### Laziness

Laziness konsepti fonksiyonel programlama dünyasından gelmektedir. Bu konseptteki motivasyonu aşağıdaki kod ile
açıklayalım:

```
large_list
  .map{|e| slow_complex_method (e)}
  .take(5)
```

Bu kodda `slow_complex_method` çok maliyetli bir işlemdir ve bu maliyetli işlem Laziness konseptine sahip bu durumda
yalnızca ihtiyaç duyulduğunda çağrılır.

Bir collection pipeline'ın lazy olabilmesi için tembel bir akıl ile inşa edilemsine ihtiyaç vardır. Clojure ve Haskell
gibi bazı fonksiyonel diller bunu daha en başında yaparlar. Diğer durumlarda laziness, Java ve Ruby gibi bazı lazy 
collection implementation'larına sahip dillerde özel bir koleksiyon sınıfı grubuna dahil edilebilir.

### Parallelism

Birçok pipeline operation doğası gereği parallel invocation'la uyumlu çalışır. Map kullanılırsa, map'i bir element için
kullanmanın sonuçları collection'daki diğer hiçbir element'e bağlı değildir. Birden çok çekirdeğe sahip bir platformda
çalışılıyorsa map evaluation'ları birden çok thread'e dağıtarak bundan yararlanılabilinir.

Birçok platform, evaluation'ları buna benzer şekilde paralel olarak dağıtma yeteneğini içerir. Büyük bir set üzerinde 
karmaşık bir function çalıştırıyorsanız, çok çekirdekli işlemcilerden yararlanarak önemli bir performans artışı 
sağlanabilir.

Ancak parallelism her zaman performansı artırmaz. Bazen parallel distribution kurmak, parallelism'den elde ettiğinizden
daha fazla zaman alır. Parallelism işleminin gerçekten herhangi bir performans iyileştirmesi sağlayıp sağlamadığını
doğrulamak için performans testleri kullanmalısınız. 

### Immutability

Collection-pipelines doğası gereği immutable veri yapılarına uygundur. Her işlemin çıktısı için yeni bir collection 
oluşturulur. Çok büyük veri yığınlarının kullanıldığı durumlarda çok fazla kopyalama gerekir ama pratikte küçük veri
yığınları ile birlikte kullanıldığında bir performans sorunu olmaz.

Bu bir sorun haline geldiğinde immutability'yi sürdürmek adına bu yol ile transform etmek üzere tasarlanmış veri 
yapılarını kullanabilirsiniz.

### Debugging

### When to Use It