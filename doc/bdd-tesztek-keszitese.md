# BDD tesztek Dokumentációja

## 1. Branch létrehozása
Létrehoztunk egy új branch-et `3-bdd-tesztek-készítése-cucumber-specflow` néven.

## 2. A projekt és a tesztek megvizsgálása
A Pinto projektet a README.md és a forráskód alapján megvizsgáltuk, és a meglévő teszteket lefuttattuk.

## 3. Cucumber felkonfigurálása
Létrehoztunk egy cucumber mappát. A build.gradle fájlhoz hozzáadtuk a cucumber task-ot, amivel a teszteket lehet futtatni. Beállítottuk a ‘step definition’-öket tartalmazó mappát.
```gradle
task cucumber() {
   dependsOn assemble, testClasses
   doLast {
       javaexec {
           main = "io.cucumber.core.cli.Main"
           classpath = configurations.cucumberRuntime + sourceSets.main.output + sourceSets.test.output
           args = ['--plugin', 'pretty', '--glue', 'cucumber', 'test/resources']
       }
   }
}
```

## 4. Feature fájlok megírása
A cucumber mappában .feature kiterjesztésű fájlokat hoztunk létre, amikbe az előre megbeszélt tesztjeink pszeudo-kódját írtuk. Két külön Feature-ra fogalmaztunk meg 3-3 esetet, melyekkel a program több funkcióját teszteltük.
Megírtuk a ParameterTypes osztályt, amiben a feature fájlokban lévő táblázatokat átalakító metódusok vannak.


## 5. Step definition-ok megírása
A Feature fájlok pszeudokódjai alapján, Annotációk segítségével Java DBB teszteket írtunk. A teszteléshez két külön osztályt használtunk fel: ReadStepDefinitions, WriteStepDefinitions. A Feature fájlok Annotáció miatt nem kellett megadnunk a metódusok sorrendjét, mert azok a pszeudo-kód sorrendjében futottak le.

Tesztek:
- Feature: ReadTest
    - Scenario: Read primitives
    - Scenario: Read mixed
    - Scenario: Read primitive lists
- Feature: WriteTest
    - Scenario: Write Primtives
    - Scenario: Write Mixed


## 6. Build, ellenőrzés,Git adminisztráció.
A gradle cucumber - parancs segítségével lefuttattuk a teszteket és pull requestel jeleztük a feladat elkészültét.
