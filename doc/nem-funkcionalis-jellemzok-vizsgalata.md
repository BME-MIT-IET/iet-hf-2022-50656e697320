#Nem funkcionális jellemzők vizsgálata
A projekt adotságai miatt a feladatból a stresszteszt volt számunkra releváns.

## 1. Új teszt fájl létrehozása
Létrehoztuk a StressTest test file-t a meglévő RDFMapperTests mintájára.

## 2. Teszt adatok készítése
Java kódban létrehoztunk egy tömböt 500.000 Person objektummal, majd ehhez csináltunk egy erőforrás fájlt az ellenőrzéshez.

## 3. Tesztet futtató gépek erőforrásai
gép1 spec:
- CPU: i5 4mag 2.30Ghz
- Memória: 8Gb

gép2 spec:
- CPU: i7 4mag 4.00Ghz
- Memória: 16Gb

## 4. Teszt futtatása
gép1: 11 sec átlagosan
gép2: 7 sec átlagosan

## 5. Teszt kiértékelő
A tesztek ellenőrzését végző függvény (Models.isomorphic) a gép1-en 24 perc 48 másodperc alatt futott le csupán 100.000 Person objektum esetén, ezért kivettük a tesztből a kiértékelést.

## 6. Eredmények értelmezése
Nagy mennyiségű adatokat megfelelő gyorsasaággal tud JavaBean objektumról RDF-re alakítani.
