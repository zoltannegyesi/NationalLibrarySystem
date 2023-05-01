# National Library System

## Az alábbiak nélkül a megadott weboldalon nem lehet az alkalmazást használni, szükséges végrehajtani azokat!

1. Az alkalmazás használatához szükség van internet kapcsolatra.
2. Szükség van JDK 8-ra, gitre, mavenre, egy MySQL adatbázis-kezelőre és szerverre, ezeket telepíteni szükséges.
3. Létre kell hozni a szükséges adatbázist, illetve usert. Az SQL szkript az nls.sql fájlban található.
4. Ez után el lehet indítani az alkalmazást, amit CMD-ből, vagy IDE-ből, vagy terminálból lehet elindítani a "mvn spring-boot:run" paranccsal, amit a git repositoryból már leklónozott mappában kell lefuttatni.
5. Ha az előzőeket sikeresen elvégeztük, a konzolban megjelenik a nagy Spring logó, ami azt jelenti, hogy fut az alkalmazás.
6. Majd az alkalmazást a https://nls.xoaf.eu/ url-en el lehet érni.

Alapértelmezetten az adatbázisban a táblákat a program minden újraindításkor droppolja és újra létrehozza. Ha azt szeretnénk, hogy újraindítás után is a már elmentett adatokkal dolgozzon, az application.properties fájlban a "spring.jpa.hibernate.ddl-auto" értékét "create-drop"-ról "update"-ra át kell írni.
