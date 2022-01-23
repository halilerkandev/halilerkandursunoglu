# Hafta 4

## Kodlama Kısmı

- İkinci ve Üçüncü ödevlerde yaptığınız geliştirmelerin testlerini yazınız. (Minimum 5 Test integration - unit tercihi size kalmış.)

- Veritabanı olarak h2 (embedded veritabanı) veya Docker’ da çalışan Postgresql kullanabilirsiniz.

### Uygulamayı Ayağa Kaldırmadan Önce

Postgres ve Redis Docker Image'larını oluşturmak için terminalde aşağıdaki komutu çalıştırın:

```bash
sh infra-up.sh
```

Uygulamanın çalışmasını sonlandırdıktan sonra oluşturduğumuz Docker Image'leri aşağıdaki komutla kaldırmak isteyebilirsiniz:

```bash
sh infra-down.sh
```

Dosyalara okuma izni vermek için ise:

```bash
chmod +x ./infra-up.sh
```

komutunu çalıştırın.

### Swagger

Link: **<http://localhost:8080/swagger-ui/index.html>**

## Araştırma Kısmı

### 1. [The Practical Test Pyramid](https://martinfowler.com/articles/practical-test-pyramid.html) yazısını okuyup özetini çıkarınız

[Cevabın bulunduğu dosyanın linki](https://martinfowler.com/articles/practical-test-pyramid.html)

### 2. Regression test nedir ? Kısaca açıklayınız

### 3. A/B test nedir ? Kısaca açıklayınız

### 4. Black box / white box test kavramlarını açıklayınız

### 5. Mutation test nedir ? Kısaca açıklayınız

### 6. Behavior Driven Development (BDD) nedir, neyi amaçlamaktadır ?

### 7. Agile test quadrant nedir ? Quadrant’ların kapsamını kısaca açıklayınız
