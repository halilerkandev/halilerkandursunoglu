# Ödev 1

## Araştırma Soruları

1. Pass by value, pass by reference kavramları nedir? Java ile ilişkili olarak açıklayınız.
2. Immutability nedir, neden önemlidir? Bir Java sınıfı nasıl immutable yapılır?
3. Framework ve library arasındaki fark nedir?
4. Java’da Garbage Collector’un görevi nedir?
5. Memory leak nedir? Java’da memory leak oluşması mümkün müdür?
6. Yeni Java sürümleri ne sıklıkla çıkmaktadır?
7. Stack ve Heap nedir? Java’da hangi yapılar stack ile, hangi yapılar heap ile ilişkilidir?
8. OpenJDK ve OracleJDK arasındaki farklar nelerdir?
9. @FunctionalInterface anotasyonu nerelerde kullanılabilir, neleri sağlar?
10. Java’da hangi functional interface’ler yer almaktadır? Yaptığınız araştırmada en popüler/göze çarpanlar hangileridir?

---

## Cevaplar

### 1. Pass by value, pass by reference kavramları nedir? Java ile ilişkili olarak açıklayınız.

pass-by-value ve pass-by-reference method'lara arguments geçmek için kullanılan çok yaygın iki mekanizmadır.
Diller bu konseptleri farklı yollar kullanarak uygularlar. Java'da ise pass-by-value mekanizması kullanılır.

Bir parametre pass-by-value olduğu zaman, caller ve callee method'lar birbirinin kopyası olan farklı iki variable
üzerinde çalışırlar. Bir variable'ın değişmesi diğer variable'ı etkilemez. Bunun anlamı, bir method'u çağırırken
parametrelerin geçildiği callee method'un geçilen parametrelerin birer clone'unu kullanabilecek olmasıdır.

Bir parametre pass-by-reference olduğu zaman ise, caller ve callee method'lar aynı object üzerinde çalışırlar.
Bunun anlamı, bir variable pass-by-reference olduğu zaman method'a bu object'in unique identifier'ının geçiliyor 
olmasıdır. Bu yüzden geçilen parametrenin instance'ında gerçekleşecek olan bir değişim orijinal value'da görülecektir.

Java'da Primitive variable'ların asıl değeri stack memory'de tutulurken Non-Primitive variable'ların ise o object'in
heap memory'deki yerini işaret eden reference'ı stack memory'de tutulur. Bu yüzden çağrılan method'a geçilen
Primitive bir variable üzerinde değişiklik yaptığımız zaman orijinal değeri etkilemezken, Non-Primitive bir variable
üzerinde yapılacak bir değişiklik aynı object'i işaret ettiği için orijinal değeri değiştirecektir.

### 2. Immutability nedir, neden önemlidir? Bir Java sınıfı nasıl immutable yapılır?

Java'da Immutable bir class'ın anlamı, class'ın bir instance'ı oluşturulduktan sonra o instance'ın field'larının artık
değiştirilemeyecek olmasıdır.

Java'da Integer, Boolean, Byte, Short gibi bütün wrapper class'lar ile String class'ı immutable'dır. Ayrıca başka 
immutable class'lar da oluşturmak mümkündür.

Immutable class oluşturabilmek için aşağıdaki şartlar yerine getirilmelidir:

- Class, final olarak deklare edilmelidir. Bu sayede child class'ların oluşturulmasının önüne geçilir.
- Class'ın field'ları private olarak deklare edilmelidir. Böylece bu field'lara direct access engellenmiş olunur.
- Class'ın field'ları ayrıca final olarak da deklare edilmelidir ki object creation'dan sonra bu field'ların 
  value'larını değiştiremeyelim.
- Constructor'da class'ın field'ları parametrelerin deep copy'leri ile initialize edilmelidir ki field'ların object
  reference ile değiştirilmesine mani olalım.
- Getter method'larda deep copy field'ların object reference'larını return etmemek için kendisini değil deep copy'lerini
  return etmeliyiz.

### 3. Framework ve library arasındaki fark nedir?

Framework uygulamanızı geliştirirken uygulama akışınızı çok büyük oranda etkiler. Bu etki oranı kullanılan
framework'lere göre değişkenlik gösterebilir.

Örneğin Angular ve Vue framework'lerini karşılaştıracak olursak Angular, Vue'ya nazaran uygulama akışınız üzerinde çok
daha etkindir. Library ise uygulama akışınız üzerine etki etmez, uygulamanızın akışından siz sorumlusunuzdur. Library
için de jQuery'yi örnek olarak gösterebiliriz.

### 4. Java’da Garbage Collector’un görevi nedir?

Garbage Collection (GC), JVM heap space'de mevcut olan object'lerin herbirini takip eder ve kullanım dışı olanları
kaldırır.

GC, Mark ve Sweep olarak adlandırılan iki adımı kullanarak çalışır:

- Mark adımında Garbage Collector memory'de hangi bölümlerin kullanımda olup olmadığını identify eder.
- Sweep adımında ise mark adımında kullanım dışı olarak işaretlenmiş object'ler kaldırılır.

Avantajları:

- Kullanımda olmayan memory space GC tarafından otomatik olarak handle edildiği için manuel olarak memory 
  allocation/deallocation handle etmeye lüzum olmaz.
- Dangling Pointer handle etmek ek yük oluşturmaz.
- Memory Leak yönetimi GC tarafından tam olarak garanti edilmese de çok büyük bir bölümü otomatik olarak handle edilir.

Dezavantajları:

- JVM, object reference creation/deletion'ın takibini yaptığından bu yana bu takip orijinal uygulamadan daha fazla
  CPU gücüne ihtiyaç duyuyor. Büyük hafıza kullanımları gerektiğinde performans taleplerine etkisi olacaktır.
- Programcıların, artık ihtiyaç duyulmayan nesneleri serbest bırakmak için ayrılmış CPU zamanının çizelgelenmesi
  üzerinde hiçbir kontrolü yoktur.
- Bazı GC implamantasyonlarının kullanımı uygulamanın beklenmedik bir şekilde durmasına yol açabilir.

### 5. Memory leak nedir? Java’da memory leak oluşması mümkün müdür?

Memory Leak, heap memory'de artık kullanılmayan object'lerin Garbage Collector tarafından memory'den kaldırılmaması
durumudur. Hafıza kaynaklarının kullanımını engellediği ve zaman içerisinde sistem performansını olumsuz yönde
etkilediği için memory leak istenmeyen bir durumdur ve bu durum Java'da handle edilmezse uygulama en sonunda
kaynaklarını tüketecek ve sonunda ölümcül bir Java.lang.OutOfMemoryError ile sona erecektir.

Heap memory'de referenced ve unreferenced diye iki farklı nesne türü bulunur. Referenced nesneler, uygulama içinde
hala aktif referansları olanlardır, buna karşın unreferenced nesnelerin aktif referansları yoktur.

Garbage Collector unreferenced nesneleri periyodik olarak kaldırır, ancak hala referenced durumda bulunan nesneleri
asla toplamaz.

Memory Leak olduğunun belirtileri:

- Uygulamanın uzun süre çalıştığı durumlarda yaşanan ciddi performans düşüşü,
- Uygulamada görülen OutOfMemoryError heap hatası,
- Uygulamada garip bir şekilde kendiliğinden yaşanan çökmeler,
- Uygulamada ara sıra connection object'lerde yaşanan tükenme.

Java'da memory leak yaşanmasına yol açan en yaygın sebepler:

#### 5.a. static Field'lardan kaynaklı memory leak

static variable'ların yoğun olarak kullanılması memory leak yaşanma potansiyelini arttıracaktır. Çünkü static field'lar
ClassLoader Garbage Collection için uygun hale gelmediği müddetçe genellikle uygulamanın lifetime'ı kadar büyük life
span'e sahiplerdir.

Bunu nasıl önleyebiliriz:

- static variable'ların kullanımını minimize ederek,
- Singleton'lar kullanırken eagerly loading yerine object'i lazily loads eden bir implamantasyonu tercih edin.

#### 5.b. Unclosed Resources kaynaklı

Ne zaman yeni bir connection kurulsa veya bir stream açsak, JVM bu kaynaklar için bellek ayırır. Veritabanı bağlantıları,
input streams ve session objects buna verilebilecek örneklerdendir.

Bu kaynakları kapatmayı unutmak, belleği bloke edebilir ve böylece onları GC'nin erişiminden uzak tutabilir.
Hatta durum öyle bir hal bile alabilir ki uygulamada bu kaynakları kapatmak için yazılmış koda erişimi engelleyebilir.

Her iki durumda da, kaynaklardan kalan açık bağlantı belleği tüketir ve bunlarla ilgilenmezsek performansı düşürebilir
ve hatta OutOfMemoryError ile sonuçlanabilir.

Bunu nasıl önleyebiliriz:

- Kaynakları kapatmak için her zaman finally block kullanın,
- Final block içinde olsa bile kaynakları kapatan kodun kendisinin hiçbir exception'ı olmaması gereklidir,
- Java 7 ve üzeri versiyonları kullanıyorsak try-with-resources statement'ını kullanabiliriz.

#### 5.c. Uygun olmayan equals() ve hashCode() implementation'ları

Yeni class'lar tanımlanırken çok yaygın bir biçimde equals ve hashCode method'larının override edilmesi gözden
kaçırılır. HashSet ve HashMap bu method'ları birçok işlemde kullanır ve doğru şekilde override edilmedikleri takdirde
olası memory leak problemleri için bir kaynak haline gelebilirler.

Bunu nasıl önleyebiliriz:

- Genel bir kural olarak yeni bir class oluşturduğunuzda mutlaka equals ve hashCode methodlarını override edin,
- Ancak bu method'ları doğru bir şekilde override etmek de gereklidir.

#### 5.d. Outer Class'lara referans veren Inner Class'lar

Non-static inner class'lar (anonymous class'lar) olduğu durumda gerçekleşir. Bu inner class'lar initialize edilebilmek
için her zaman kendisini çevreleyen outer class'ın bir instance'ına ihtiyaç duyar.

Non-static bütün inner class'lar, varsayılan olarak, kendisini kapsayan class'a yönelik bir implicit reference'a
sahiptir. Uygulamamızda bu inner class'a ait object'i kullanırsak, outer class object'i kapsam dışına çıktıktan sonra
bile Garbage Collector tarafından kaldırılamayacaktır.

Bunu nasıl önleyebiliriz:

- Inner class'ın outer class üyelerine erişmesi gerekmiyorsa, onu statik bir class'a dönüştürmeyi düşünün.

#### 5.e. finalize() Methods

Finalizer'ların kullanımı potansiyel memory leak'lerin başka bir kaynağıdır. Bir class'ın finalize methodu override
edildiğinde o class'ın nesneleri anında Garbage Collection tarafından kaldırılmaz. Bunun yerine GC onları daha
sonraki bir zamanda finalization olması için bir queue'ya sokar. Buna ek olarak, finalize methodunda yazılan kod
optimal değilse ve finalizer queue Java Garbage Collector'a ayak uyduramıyorsa eninde sonunda uygulama
OutOfMemoryError ile karşılaşacaktır.

Bunu nasıl önleyebiliriz:

- Finalizer'lardan mümkün olduğunca kaçınmalıyız.

#### 5.f. Interned Strings

Java 7'de Java String pool, PermGen'den HeapSpace'e aktarıldığında büyük bir değişiklik geçirmişti.
Ancak sürüm 6 ve daha düşük sürümlerde çalışan uygulamalar için büyük String'lerle çalışırken daha dikkatli olmalıyız.

Çok büyük bir String nesnesi okursak ve bu nesne üzerinde intern() method'unu çağırırsak, bu, PermGen'de
(permanent memory) bulunan ve uygulamamız çalıştığı sürece orada kalacak olan dize havuzuna gider.
Bu, belleği bloke eder ve uygulamamızda büyük bir memory leak oluşturur.

Bunu nasıl önleyebiliriz:

- En kestirme ve basit yol Java sürümünü 7 ve sonrasına taşımak olacaktır,
- Büyük String'ler üzerinde çalışıyorsanız olası bir OutOfMemoryError'dan kaçınmak için PermGen alanının boyutunu
artırın.

#### 5.g. ThreadLocals kullanımı

ThreadLocal'lar kendilerini tutan thread'ler artık ayakta olmadığı zaman Garbage Collection tarafından kaldırılmaları
gerekir. Problem, ThreadLocal'lar modern uygulama sunucuları tarafından kullanıldığında ortaya çıkar.

Bu modern uygulama sunucuları gelen talepleri işlemek için yeni thread oluşturmak yerine thread havuzunu kullanırlar.
Daha da fazlası ayrı bir Classloader kullanırlar.

Bu thread havuzunda thread'ler başka işler için tekrardan kullanılmak üzere tutuldukları için Garbage Collection
tarafından kaldırılmazlar.

Bu durumda herhangi bir sınıf ThreadLocal değişkeni oluşturur ancak bunu sonrasında açıkça kaldırmazsa, o object'in bir
kopyası o Thread'de uygulama durdurulmuş olsa bile kalmaya devam edecek, dolayısı ile kaldırılmasının önüne
geçilecektir.

Bunu nasıl önleyebiliriz:

- ThreadLocals'ı artık kullanılmadıklarında kaldırmak iyi bir pratiktir. ThreadLocal'lar, mevcut thread'in bu değişken
  için değerini kaldıran `remove()` method'unu sağlar.
- Değeri silmek için `ThreadLocal.set(null)` kullanmayın. Aslında değeri temizlemez, bunun yerine geçerli thread ile
  ilişkili bir Map arar ve key/value çiftini sırasıyla o andaki thread ve null olarak ayarlar.
- ThreadLocal'ı, exception'da bile her zaman kapalı olduğundan emin olmak için `finally` bloğunda kapatılması gereken
  bir kaynak olarak düşünmek daha da iyidir.

### 6. Yeni Java sürümleri ne sıklıkla çıkmaktadır?

Java 9 (non-LTS) sürümü ile birlikte yeni Java sürümleri 6 aylık periyotlar halinde çıkmaya başlamıştır.

Java sürümlerinin daha kısa periyotlar halinde çıkması dilin gelişiminin daha küçük olmasını ve bu gelişimin daha
yönetilebilir ve daha kolay benimsenebilir olmasını sağlar.


### 7. Stack ve Heap nedir? Java’da hangi yapılar stack ile, hangi yapılar heap ile ilişkilidir?

JVM bir uygulamayı en optimal yol çalıştırmak için memory'yi stack ve heap memory olarak ikiye ayırır. Yeni bir variable
ya da object oluşturduğumuzda, bir method çağrıldığında, bir String deklare edildiğinde ya da benzer operasyonlar 
gerçekleştiğinde JVM bu işlemler için stack ya da heap memory'lerden birini kullanır.

Java'da stack memory, static memory allocation ve thread execution için kullanılır. Stack memory bir method'a geçilen
primitive değerleri ve object referansları tutar.

Stack memory'ye erişim LIFO (Last-in-first-out) düzeni ile gerçekleşir. Ne zaman yeni bir method çağrılırsa o method'a
özel değerleri içeren bir block oluşturulur ve bu block stack'te en üste yerleştirilir.

Method'un execution'ı sonlandığında method'a karşılık gelen stack frame boşaltılır, akış çağrılan method'un öncesine
geçer ve boşaltılan alan çağrılacak sonraki method için kullanılabilir hale gelir.

Stack memory'ye ait diğer özellikler şunlardır:

- Yeni method'lar çağrıldıkça ve döndürüldükçe memory'de büyür ve küçülür.
- Stack'teki değişkenler yalnızca onları oluşturan method çalıştırıldığı sürece var olurlar.
- Stack otomatik olarak tahsis edilir veya geri alınır methodun çalışması sonlanınca.
- Memory doluysa `java.lang.StackOverFlowError` exception'ı fırlatılır.
- Stack memory'ye erişim Heap memory'ye olan erişime kıyasla hızlıdır.
- Stack memory threadsafe'tir, çünkü her thread kendisine ait stack memory üzerinde çalışır.
 
Heap space Java object'lerin ve JRE class'ların runtime'da dynamic memory allocation'ı için kullanılır. Yeni object'ler
her zaman heap space'te oluşturulur ve bu object'lere olan referanslar da stack memory'de tutulur.

Bu object'lere global erişim vardır ve uygulamanın her yerinden bu object'lere erişim sağlayabiliriz.

Bu memory modelini generation'lar olarak daha küçük parçalara ayırabiliriz:

- **Young Generation** - oluşturulan tüm yeni object'lerin bulundurulduğu ve belli bir süreye kadar tutulduğu yerdir.
  Burası dolduğunda küçük çaplı bir Garbage Collection meydana gelir.
- **Old or Tenured Generation** - uzun süre hayatta kalan object'lerin tutulduğu yer burasıdır. Young Generation'da
  tutulan nesneler belli bir hayatta kalma süresine ulaştıklarında daha eski bir nesle taşınırlar.
- **Permanent Generation** - burası runtime class'lar ve uygulama method'ları bulunduran JVM metadata'yı içerir. 

Heap memory'ye ait diğer özellikler şunlardır:

- Young Generation, Old veya Tenured Generation ve Permanent Generation gibi karmaşık memory management teknikleri 
  aracılığıyla erişilir.
- Memory doluysa `java.lang.OutOfMemoryError` exception'ı fırlatılır.
- Buraya erişim Stack memory'ye olan erişime kıyasla daha yavaştır.
- Heap stack otomatik olarak deallocate edilmez. Bellek kullanımının verimliliği adına kullanılmayan object'leri
  kaldırmak için Garbage Collection'a ihtiyaç duyulur.
- Heap threadsafe değildir, kodun düzgün bir şekilde senkronize edilmesiyle korunması gerekir.

### 8. OpenJDK ve OracleJDK arasındaki farklar nelerdir?

#### 8.a. Sürüm Takvimi

OracleJDK sürümleri 3 yılda bir çıkarken, OpenJDK 6 ayda bir çıkar.

Oracle, sürümleri için uzun vadeli destek sağlar. Öte yandan, OpenJDK bir sürümdeki değişiklikleri yalnızca bir sonraki
sürüm yayınlanana kadar destekler.

#### 8.b. Lisanslar

Oracle, Oracle Binary Code License Agreement altında lisanslanmıştır. Oysa OpenJDK, GNU General Public License (GNU GPL)
version 2'ye sahiptir.

Oracle platformunu kullanmanın lisanslama açısından bazı etkileri vardır. Oracle'ın duyurması üzerine 
Oracle Java SE 8'e gelen public güncellemeler Ocak 2019'dan sonra commercial license olmaksızın business, commercial,
ya da production için kullanılamayacak. Ancak OpenJDK tamamen açık kaynak kodludur ve özgürce kullanılabilir.

#### 8.c. Performans

Oracle JDK'in geliştirilme süreci OpenJDK'i baz aldığı için ikisi arasında büyük bir teknik farklılık bulunmamaktadır.

Performans söz konusu olduğunda, Oracle'ınki yanıt verme ve JVM performansı açısından çok daha iyidir. 
Kurumsal müşterilerine verdiği önem nedeniyle stability'ye daha fazla önem vermektedir.

OpenJDK ise sürümleri daha sık sunacak ve bunun bir sonucu olarak instability ile ilgili sorunlarla 
karşılaşılabilinecektir. Topluluk geri bildirimlerine dayanarak, bazı OpenJDK kullanıcılarının performans sorunlarıyla
karşılaştığını biliyoruz.

#### 8.d. Feature'lar

Feature'ları ve option'ları karşılaştıracak olursak Oracle ürününün Flight Recorder, Java Mission Control ve
Application Class-Data Sharing özelliklerine sahip olduğunu, OpenJDK'nın ise Font Renderer özelliğine sahip olduğunu
göreceğiz.

Ayrıca Oracle, daha fazla Garbage Collection option'ınına ve daha iyi renderer'lara sahiptir.

#### 8.e. Gelişim ve Popülarite

Oracle JDK tamamen Oracle Corporation tarafından geliştirilmiştir, OpenJDK ise Oracle, OpenJDK ve Java Topluluğu
tarafından geliştirilmiştir. Ancak Red Hat, Azul Systems, IBM, Apple Inc., SAP AG gibi en iyi şirketler de 
geliştirmesinde aktif rol alıyor.

Popülerlik söz konusu olduğunda Android Studio ya da IntelliJ IDEA gibi araçlara sahip en iyi şirketler Oracle'ı 
tercih ederlerdi ancak her ikisi de OpenJDK tabanlı JetBrains yapılarına geçtiler.

### 9. @FunctionalInterface anotasyonu nerelerde kullanılabilir, neleri sağlar?

@FunctionalInterface anotasyonunu custom Functional Interface'ler oluştururken kullanırız. Bu anotasyonu her Functional
Interface oluşturduğumuzda kullanmamız tavsiye edilir. Functional Interface'lerin instance'ları lambda expression'larla,
method reference'larla ya da constructor reference'larla oluşturulabilir.

Bir Functional Interface herhangi bir sayıda static ve default method içerebilir ancak sadece bir tane abstract method
mutlaka içermelidir. Bu sebeple bu yapılar Single Abstract Method Interfaces ya da SAM Interfaces olarak da bilinirler.
Java 8 ile birlikte gelen yeni bir özelliktir ve functional programming yaklaşımının uygulanmasında çok önemli bir
yere sahiptir. Bir Functional Interface bir başka Interface'i abstract methodu olmaması koşulu ile extend edebilir.

### 10. Java’da hangi functional interface’ler yer almaktadır? Yaptığınız araştırmada en popüler/göze çarpanlar hangileridir?

Java'daki Predefined-Functional Interfaces şunlardır:

#### 10.a. Functions

Bir değer alan ve diğer bir değeri dönen en basit ve genel lambda functional interface'idir. Gösterim şekli:
`public interface Function<T, R> { … }` böyledir.

Function tipinin standard library'deki örnek kullanımlarından biri `Map.computeIfAbsent` method'udur. Bu method bir map
key'den değer döner, ancak map'te o key yoksa bir değer hesaplar. Bu değeri hesaplamak için ise bir Function
implementation geçilmesi gereklidir parametre olarak.

```
Map<String, Integer> nameMap = new HashMap<>();
Integer value = nameMap.computeIfAbsent("John", s -> s.length());
```

#### 10.b. Primitive Function Specializations

Primitive bir type, generic type argument olamayacağından, en çok kullanılan double, int, long primitive type'ların 
kombinasyonları argument ve return tiplerine denk gelecek şekilde farklı özelleşmiş Function Interface'nin 
versiyonları vardır.

Örneğin, `DoubleFunction, ToLongFunction, DoubleToLongFunction` gibi.

#### 10.c. Two-Arity Function Specializations

Lambda'ların iki argument içeren halleridir. Bu yüzden interface isimlerinde **Bi** keyword'ünü içerirler.

Örneğin, `BiFunction, ToDoubleBiFunction` gibi.

#### 10.d. Suppliers

Supplier functional interface argument almaz. Genellikle değerlerin lazy generation'ı için kullanılırlar.
Örnek olarak bir double'ın karesini döndüren fonksiyon tanımlayalım.

```
public double squareLazy(Supplier<Double> lazyValue) {
    return Math.pow(lazyValue.get(), 2);
}

Supplier<Double> lazyValue = () -> {
    Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
    return 9d;
};

Double valueSquared = squareLazy(lazyValue);
```

#### 10.e. Consumers

Supplier'ın tersi olarak bir argument alır fakat herhangi bir değer return etmez. Örneğin aşağıda List.forEach methoduna
bir Consumer functional interface'i olan lambda geçilmiştir:

```
List<String> names = Arrays.asList("John", "Freddy", "Samuel");
names.forEach(name -> System.out.println("Hello, " + name));
```

Ayrıca `DoubleConsumer` gibi özelleşmiş versiyonları da mevcuttur.

#### 10.f. Predicates

Bir predicate bir değer alır ve sonucunda bir boolean değer döner.

```
List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");

List<String> namesWithA = names.stream()
  .filter(name -> name.startsWith("A"))
  .collect(Collectors.toList());
```

Ayrıca Consumer gibi Predicate'ın da `DoublePredicate` gibi özelleşmiş versiyonları bulunmaktadır.

#### 10.g. Operators

Operator'lar aldıkları argument ile aynı tipe sahip bir değer döndüren Functional Interface'lerdir.

```
List<String> names = Arrays.asList("bob", "josh", "megan");

names.replaceAll(name -> name.toUpperCase());
```

#### 10.h. Legacy Functional Interfaces

Bütün Functional Interface'ler Java 8 ile birlikte ortaya çıkmadı. Önceki versiyonlardan kalma birçok interface
FunctionalInterface kısıtlarına uymakta ve onları lambda'lar gibi kullanabilmekteyiz. Concurrency API'larda yaygın
olarak kullanılan Runnable ve Callable interface'leri öne çıkan örneklerdendir. Java 8'de bu interface'ler
@FunctionalInterface annotation ile işaretlenmişlerdir. Aşağıdaki örnek bize çok iyi basitleştirilmiş bir
concurrency code sunar:

```
Thread thread = new Thread(() -> System.out.println("Hello From Another Thread"));
thread.start();
```