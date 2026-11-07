# Dokumentasi Alur dan Kode Sistem Game

Dokumen ini menjelaskan susunan program, alur eksekusi, dan fungsi setiap file
Java dalam proyek.

## 1. Gambaran Umum

Program mensimulasikan dua tipe hero dalam game:

- **Warrior**: menyerang dengan pedang dan mengurangi damage menggunakan defense
  serta armor.
- **Mage**: menyerang dengan sihir dan menggunakan mana untuk skill.
- **Hero**: superclass abstrak yang menyimpan data dan perilaku umum hero.

Interface memisahkan kemampuan yang dapat dimiliki suatu objek:

- `Attacker`: kemampuan menyerang.
- `Defender`: kemampuan bertahan dan mengurangi damage.
- `GameElement`: kemampuan mengaktifkan skill.

## 2. Struktur File

```text
src/main/java/responsipbo/
  Main.java
  contract/
    Attacker.java
    Defender.java
    GameElement.java
  model/
    Hero.java
    Mage.java
    Warrior.java
```

`package` pada setiap file sesuai dengan letak foldernya. `Main` menggunakan
package `responsipbo`, interface berada di `responsipbo.contract`, dan kelas
hero berada di `responsipbo.model`.

## 3. Alur Eksekusi Program

Titik awal program adalah `main` di `Main.java`.

```text
Mulai
  |
  +-- Tampilkan nama game
  |
  +-- Buat Warrior Arkan dan Mage Nyra
  |
  +-- Buat objek Warrior Boris melalui referensi Attacker
  +-- Buat objek Warrior Damar melalui referensi Defender
  |
  +-- Tampilkan status awal Arkan dan Nyra
  |
  +-- Jalankan aksi serangan Warrior dan Mage
  |     +-- Serangan mengirim damage ke target.kurangiHp(...)
  |     +-- Target menghitung pertahanan dan memperbarui HP
  |
  +-- Jalankan aksi bertahan Arkan dan skill Nyra
  |
  +-- Boris menyerang Nyra melalui referensi Attacker
  +-- Damar mengurangi damage melalui referensi Defender
  |
  +-- Tampilkan status akhir Arkan dan Nyra
  +-- Tampilkan daftar konsep yang diperagakan
  |
Selesai
```

### Contoh hitungan dari data program

1. Arkan memiliki attack `28`, sehingga damage serangannya `28 + 8 = 36`.
   Nyra memiliki defense `10`; damage yang diterima `36 - 10 = 26`, sehingga HP
   Nyra berubah dari `120` menjadi `94`.
2. Nyra memiliki attack `32` dan mana `60`. Damage sihirnya `32 + (60 / 10) =
   38`. Arkan memiliki defense `18` dan armor `12`; damage yang diterima `38 -
   (18 + 12) = 8`, sehingga HP Arkan berubah dari `170` menjadi `162`.
3. Skill Nyra memakai `10` mana, dari `60` menjadi `50`.
4. Boris menyerang Nyra dengan damage `36`. Setelah defense Nyra sebesar `10`,
   Nyra menerima `26` damage dan HP-nya menjadi `68`.
5. Untuk serangan masuk `30`, Damar mengurangi `18` defense dan `12` armor.
   Damage tersisa `0`, maka output menampilkan `Damage yang diterima oleh
   defenderObj: 0`.

## 4. Penjelasan Setiap File

### `Main.java`

File ini menjadi titik masuk sekaligus menjalankan demonstrasi program.

- Mengimpor interface dan kelas yang digunakan.
- Membuat `Warrior` bernama Arkan dan `Mage` bernama Nyra.
- Membuat Boris sebagai `Attacker` dan Damar sebagai `Defender`. Walaupun
  variabelnya bertipe interface, objek yang dibuat tetap `Warrior`; ini adalah
  contoh polimorfisme interface.
- Memanggil `displayStatus`, `attack`, `attackTarget`, `defend`,
  `activateSkill`, dan `reduceDamage`.
- Menampilkan status akhir agar perubahan HP dan mana dapat diamati.
- Bagian “Bukti syarat” merangkum konsep OOP yang diperagakan.

Pada daftar bukti di `Main`, ada kalimat “getter/setter private”. Yang benar:
atributnya `private`, sedangkan getter dan setter dibuat `public` agar atribut
dapat dibaca atau diubah melalui enkapsulasi.

### `contract/Attacker.java`

Interface untuk objek yang dapat menyerang.

- `attack()` menjalankan aksi serangan umum.
- `attackTarget(Hero target)` menyerang hero yang diberikan sebagai target.

`Hero` diimpor karena parameter `attackTarget` menggunakan tipe tersebut.
Interface hanya menetapkan bentuk method; implementasinya ada pada `Warrior`
dan `Mage`.

### `contract/Defender.java`

Interface untuk objek yang dapat bertahan.

- `defend()` menjalankan aksi bertahan.
- `reduceDamage(int incomingDamage)` mengembalikan damage setelah pertahanan
  diperhitungkan.

`Warrior` mengimplementasikan interface ini dan menghitung pengurangan damage
berdasarkan defense serta armor.

### `contract/GameElement.java`

Interface dengan method `activateSkill()`. `Mage` mengimplementasikannya untuk
mengaktifkan skill yang memakai mana.

### `model/Hero.java`

Superclass abstrak untuk semua hero.

- `NAMA_GAME` adalah konstanta nama game.
- `id` bersifat `private final`, sehingga tidak dapat diganti setelah
  diinisialisasi.
- `name`, `hp`, `attackPower`, `defensePower`, dan `level` adalah state umum
  hero yang disimpan sebagai atribut privat.
- Konstruktor dengan lima parameter menginisialisasi semua atribut utama.
- Konstruktor dua parameter memberikan nilai default, lalu meneruskan
  inisialisasi ke konstruktor utama dengan `this(...)`.
- Konstruktor tanpa parameter juga menggunakan constructor chaining.
- Getter dan setter menyediakan akses terkontrol ke atribut. Tidak ada setter
  untuk `id` karena nilainya tetap.
- `levelUp()` menaikkan level.
- `displayStatus()` menampilkan status umum.
- `kurangiHp(int damage)` dideklarasikan abstrak agar tiap subclass menentukan
  perhitungan damage-nya sendiri.

### `model/Warrior.java`

Subclass `Hero` yang mengimplementasikan `Attacker` dan `Defender`.

- `armor` menyimpan nilai armor khusus Warrior.
- Konstruktor memanggil `super(...)` untuk menginisialisasi bagian `Hero`.
- Konstruktor ringkas memberikan nilai awal Warrior.
- `attack()` menampilkan aksi serangan pedang.
- `attackTarget(...)` menghitung damage Warrior sebagai attack power ditambah
  bonus `8`, kemudian memanggil `target.kurangiHp(damage)`.
- `defend()` menampilkan aksi mengangkat perisai.
- `reduceDamage(...)` mengurangi damage masuk dengan defense dan armor; hasil
  tidak dibiarkan negatif.
- `kurangiHp(...)` memakai hasil pengurangan damage. Jika masih ada damage,
  HP dikurangi dan tidak boleh kurang dari nol; jika tidak, serangan ditahan.
- `displayStatus()` memanggil `super.displayStatus()` lalu menampilkan armor
  dan jenis hero.

### `model/Mage.java`

Subclass `Hero` yang mengimplementasikan `Attacker` dan `GameElement`.

- `mana` menyimpan energi untuk skill.
- Konstruktor memanggil `super(...)` untuk menginisialisasi data umum `Hero`.
- Konstruktor ringkas memberikan nilai awal Mage.
- Getter dan setter menyediakan akses ke mana.
- `attack()` menampilkan aksi mantra sihir.
- `attackTarget(...)` menghitung damage dari attack power ditambah sepersepuluh
  mana, kemudian memanggil `target.kurangiHp(damage)`.
- `activateSkill()` mengurangi mana sebanyak `10` jika cukup; jika tidak,
  program menampilkan bahwa mana tidak cukup.
- `kurangiHp(...)` mengurangi defense dari damage. Damage tidak boleh negatif;
  bila masih ada damage, HP berkurang dan dibatasi minimal nol. Jika damage
  tertahan seluruhnya, program menampilkan pesan menghindar.
- `displayStatus()` memanggil `super.displayStatus()` lalu menampilkan mana
  dan jenis hero.

## 5. Konsep OOP yang Terlihat

| Konsep | Contoh dalam program |
|---|---|
| Enkapsulasi | Atribut state pada `Hero`, `Warrior`, dan `Mage` bersifat `private`. |
| Constructor overloading | `Hero` memiliki konstruktor dengan beberapa daftar parameter. |
| Inheritance | `Warrior` dan `Mage` mewarisi `Hero`. |
| Abstract class/method | `Hero` abstrak dan mendeklarasikan `kurangiHp`. |
| Overriding | Subclass mengimplementasikan method dengan anotasi `@Override`. |
| Polimorfisme interface | Referensi `Attacker` dan `Defender` menunjuk objek `Warrior`. |
| Multiple interface | `Warrior implements Attacker, Defender`. |
| State berubah | Serangan mengubah HP dan skill mengubah mana. |

## 6. Compile dan Jalankan

Dari root proyek di PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$sources = Get-ChildItem -Recurse src\main\java -Filter *.java | ForEach-Object FullName
javac -d out $sources
java -cp out responsipbo.Main
```
