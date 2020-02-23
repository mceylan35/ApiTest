# ApiTest Projesinde Kullanılan Teknolojiler

Java, TestNG ,LOG4j, Rest Assured vb teknolojiler kullanıldı.

#ApiTest Projemize Geçelim
RestAssuredConfiguration sınıfımızda Rest Assured kütüphanemizin ortak kodlarını sürekli tekrar etmemesi için bu sınıfı oluşturdum.

Şu anlık sadece Get methoduyla ilgili işlemler yapılabiliyor ama put,post, delete gibi işlemleri yapmak için genişletebiliriz.
ITestLogger İnterface'ini birden fazla loglama kütüphanesini kullanabilmek için bu İnterface'i oluşturdum. TestLogger sınıfına implement ettim.

OmdbApiTest sınıfında getMovieById(String movieName,String filmName) methodu ile film adına göre arama yapıp apiden dönen filmlerden filmName'e göre arama yapıp bulunan filmin İmdbId'sini döndüren bir method yazdım.
searchFilmTest() Test Methodunda Harry Potter and the Sorcerer's Stone filminin Title, Year, Released bilgilerini kontrol ettim.

 
