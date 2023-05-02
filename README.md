# National Library System

## Az alábbiak nélkül a megadott weboldalon nem lehet az alkalmazást használni, szükséges végrehajtani azokat!

1. Az alkalmazás használatához szükség van internet kapcsolatra.
2. Szükség van JDK 8-ra, gitre, mavenre, egy MySQL adatbázis-kezelőre és szerverre, ezeket telepíteni szükséges.
3. Létre kell hozni a szükséges adatbázist, illetve usert. Az SQL szkript az nls.sql fájlban található.
4. Ez után el lehet indítani az alkalmazást, amit CMD-ből, vagy IDE-ből, vagy terminálból lehet elindítani a "mvn spring-boot:run" paranccsal, amit a git repositoryból már leklónozott mappában kell lefuttatni.
5. Ha az előzőeket sikeresen elvégeztük, a konzolban megjelenik a nagy Spring logó és nem jelenik meg a build failure, akkor az azt jelenti, hogy fut az alkalmazás.
6. Majd az alkalmazást a https://nls.xoaf.eu/ url-en el lehet érni.

_Amennyiben biztosan jól lettek az előzőek elvégezve és ezek után se érhető el az alkalmazás a 6. pontban szereplő linken, kérem próbálja meg újra egy pár órával, vagy egy nappal később. Előfordulhat, hogy áramszünet, szerver karbantartás történik éppen, természetesen ilyenkor nem érhető el az alkalmazás. Ha ilyen előfordul, köszönöm a türelmét!_
