## Veteriner Yonetim Sistemi

![vetSystem3](https://github.com/berkpak/Patika/assets/96004963/64c4e619-0a61-49fd-ab02-58c13b99182b)

### Klinikteki doktolarin bilgilerini mustetilerin, hayvanlarin, asilarin, randevu bilgilerini (yeni kayit ekleme, var olan kayitlari goruntuleme, guncelleme, silme ve filtreleme islemleri) CRUD islemleri yapan bir REST API projesidir.

---

### Katmanli mimari yapisi kullanilarak yapilmistir.

- Spring Boot
- Spring Web
- PostgreSQL
- Postman
- Lombok
- ModelMapper
### Teknolojileri kullanilarak yapilmistir.

---

## API Endpointler

### Customer

| HTTP | ENDPOINT |                                                           Response |
|------|:--------:|-------------------------------------------------------------------:|
| POST |  http://localhost:8080/v1/customers  |                                                 Yeni musteri ekler |
| GET  |  http://localhost:8080/v1/customers/getById/5  |                                   Musterileri ID'ye gore filtreler |
| GET  |   http://localhost:8080/v1/customers?page=0&pageSize=10    |                                     Kayitli musterileri goruntuler |
| GET  |   http://localhost:8080/v1/customers/getByName/berk    |                           Kayitli musterileri adina gore filtreler |
| GET  |   http://localhost:8080/v1/customers/4/animals    | Kayitli musterileri ID'sine gore sahip oldugu hayvanlari filtreler |
| PUT  |  http://localhost:8080/v1/customers   |                             ID'ye gore kayitli musteriyi gunceller |
| DEL  |   http://localhost:8080/v1/customers/    |                                 ID'ye gore kayitli musteriyi siler |

### Animal

| HTTP | ENDPOINT |                                Response |
|------|:--------:|----------------------------------------:|
| POST |  http://localhost:8080/v1/animals  |                       Yeni hayvan ekler |
| GET  |  http://localhost:8080/v1/animals/9  |         Hayvanlari ID'ye gore filtreler |
| GET  |   http://localhost:8080/v1/animals    |           Kayitli hayvanlari goruntuler |
| GET  |   http://localhost:8080/v1/animals/name/Odin    | Kayitli hayvanlari adina gore filtreler |
| PUT  |  http://localhost:8080/v1/animals   |    ID'ye gore kayitli hayvani gunceller |
| DEL  |   http://localhost:8080/v1/animals/9    |        ID'ye gore kayitli hayvani siler |

### Doctor

| HTTP | ENDPOINT |                             Response |
|------|:--------:|-------------------------------------:|
| POST |  http://localhost:8080/v1/doctors  |                    Yeni doktor ekler |
| GET  |  http://localhost:8080/v1/doctors/   |      Doktorlari ID'ye gore filtreler |
| GET  |   http://localhost:8080/v1/doctors?page=0&pageSize=10    |        Kayitli doktorlari goruntuler |
| PUT  |   http://localhost:8080/v1/doctors    | ID'ye gore kayitli doktoru gunceller |
| DEL  |   http://localhost:8080/v1/doctors/5    |    ID'ye gore kayitli doktoru siler  |

### Vaccine

| HTTP | ENDPOINT |                                               Response |
|------|:--------:|-------------------------------------------------------:|
| POST |  http://localhost:8080/v1/vaccines  |                                         Yeni asi ekler |
| GET  |  http://localhost:8080/v1/vaccines/8   |                           Asilari ID'ye gore filtreler |
| GET  |   http://localhost:8080/v1/vaccines?page=0&pageSize=10    |                             Kayitli asilari goruntuler |
| GET  |   http://localhost:8080/v1/vaccines/animal/5    |          Kayitli asilari hayvan ID'sine gore filtreler |
| GET  |   http://localhost:8080/v1/vaccines/protection-dates?start_date=2022-01-01&end_date=2025-01-01    | Kayitli asilari koruyuculuk tarihlerine gore filtreler |
| PUT  |   http://localhost:8080/v1/vaccines   |                     ID'ye gore kayitli asiyi gunceller |
| DEL  |   http://localhost:8080/v1/vaccines/12    |                         ID'ye gore kayitli asiyi siler |

### AvailableDate

| HTTP | ENDPOINT |                                Response |
|------|:--------:|----------------------------------------:|
| POST |  http://localhost:8080/v1/available_dates  |                          Yeni gun ekler |
| GET  |  http://localhost:8080/v1/available_dates/2   |            ID'ye gore gunleri filtreler |
| GET  |   http://localhost:8080/v1/available_dates?page=0&pageSize=10   | Doktolarin kayitli gunlerini goruntuler |
| PUT  |   http://localhost:8080/v1/available_dates    |       ID'ye gore kayitli gunu gunceller |
| DEL  |   http://localhost:8080/v1/available_dates/4    |           ID'ye gore kayitli gunu siler |

### Appointments

| HTTP | ENDPOINT |                                                                     Response |
|------|:--------:|-----------------------------------------------------------------------------:|
| POST |  http://localhost:8080/v1/appointments  |                                                           Yeni randevu ekler |
| GET  |  http://localhost:8080/v1/appointments/2   |                                             Randevulari ID'ye gore filtreler |
| GET  |   http://localhost:8080/v1/appointments?page=0&pageSize=10    |                                               Kayitli randevulari goruntuler |
| GET  |   http://localhost:8080/v1/appointments/doctorId/1?startDateTime=2023-01-01T00:00:00&endDateTime=2025-01-01T23:59:59    | Kayitli randevulari doktor ID'sine ve girilen tarih araligina gore filtreler |
| GET  |   http://localhost:8080/v1/appointments/getAnimalById/1?startDateTime=2023-01-01T00:00:00&endDateTime=2025-01-01T23:59:59    | Kayitli randevulari hayvan ID'sine ve girilen tarih araligina gore filtreler |
| PUT  |   http://localhost:8080/v1/appointments   |                                       ID'ye gore kayitli randevuyu gunceller |
| DEL  |   http://localhost:8080/v1/available_dates/3    |                                           ID'ye gore kayitli randevuyu siler |


