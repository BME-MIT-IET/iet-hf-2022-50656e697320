# Gradle build fix

## 1. Branch létrehozása
Létrehoztunk egy új branch-et `gradle` néven.

## 2. Gradle verzió frissítése
A gredle verzót átállítottuk 2.0-ról a legújabbra, 7.4.2-re.
Gradle wrapper-t újrageneráltuk.

## 3. GitHub Actions beállítása
Hozzáadtunk egy új workflow-t, ami automatikusan lefutatja a gradle által meghatározott teszteket.
Meglepően egyszerű volt beállítani, a GitHub fogta a kezünket végig.

## 4. Branch protection rule hozzáadása
Létrehoztunk egy branch szabályt, ami nem hagyja a pull request elfogadását és összefűzését, amíg legalább ketten nem nézzik át.

## 5. Pull request létrehozása
Létrehoztuk a pull request-et és hozzáadtuk a megfelelő issue-hoz, végül review-ot kértünk a többi csapatagtól.

## 6+. Dokumentáció
Megirtuk ezt a fájlt.
Megírtuk az előző sort.
Megírtuk ez előző sort.
.
.
.