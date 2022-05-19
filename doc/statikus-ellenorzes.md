

# Sonar dokumentáció 


## 1. Branch létrehozása
Létrehoztunk egy új branch-et `2-kód-átvizsgálás-statikus-analízis` néven.

## 2. A projekt és a tesztek megvizsgálása
A Pinto projektet a README.md és a forráskód alapján megvizsgáltuk, és a meglévő teszteket lefuttattuk.A statikus vizsgálat emberi szemmel nehéznek bizonyult, ezért a Sonar Cloud program segítségével újra “átnéztük” a projektet. 

## 3. SonarCloud felkonfigurálása
Miután admin jogot kaptunk a projekthez SonarCloud segítségével beállítottunk egy CI-based gradle ellenőrzést. A SonarCloud minden lépést részletesen leírt, így könnyű dolgunk lett volna, de a Github workflow működése miatt csak sokadik próbálkozásra sikerült.

## 4. Sonar Cloud javaslatainak, code smell-jelinek átnézése.

Átnézett code smellek: isInstance cseréje, Throwable cseréje, deprecated newIntance()- cseréje.

A hash algoritmus biztonságosaggra cseréje a Sonar Cloud által javasoltra, azonban ezt a változtatást nem végeztük el mert nem tartottuk szükségesnek (a Sonar Cloud szabályaiból ki lehet venni, hogy ne jelezze). 
A RDFMapper.valueToObject() függvényben egy bug javítása, ahol NullPointerExceptions dobódhatott



Aztán a “why this is an issue?” - linkre kattintva javaslatokat kaptunk a kód szépítésére, a hibák kijavítására.




## 5. Refactor, hibák javítása.
A code smell-ek a VS codeban is látszódott. Ez egyes code smell-eknek utánanéztünk a neten, illetve megvizsgáltuk, hogy a project szempontjából mennyire lényeges a változtatás.

##6.Build, ellenőrzés,Git adminisztráció.

A változtatások után a teszteket és a build-et újra futtattuk, majd a push után, látszódott a SonarCloud felületén az eredmény (kevesebb code smell) és 0 bug.


