# Proje Durumu

## Mevcut durum

- Kotlin ve Jetpack Compose kullanan ilk Android uygulama iskeleti hazırlandı.
- Uygulama açılış ekranında **SmartAlarm** başlığı ve **Alarm yok.** metni gösteriliyor.
- Android yapılandırması minSdk 26, compileSdk 35, targetSdk 35 ve Java 17 olarak ayarlandı.
- `develop` ve `main` dallarına yapılan push işlemlerinde debug APK üreten GitHub Actions iş akışı eklendi.
- Henüz alarm kurma, saklama veya zamanlama özelliği bulunmuyor.

## Sonraki adım

Alarm veri modelini ve temel alarm ekleme akışını, gerekli Android alarm izinleri ve zamanlama davranışı netleştirildikten sonra tasarlamak.
