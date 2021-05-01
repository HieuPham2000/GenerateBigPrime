# Generate Prime Number :zap::zap::zap:

This project helps us to generate prime numbers (you can even generate primes 3072-bit or more)

---

### 1. About this project :pencil:

- Use ```java.security.SecureRandom``` to generate random numbers (https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html).

- Use ```java.math.BigInteger``` to work with huge numbers in this project (https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html).

- We have 3 classes: ```Check.java``` contains some methods to check if a number is prime number; ```Generate.java``` contains methods to generate random numbers and random prime number; ```Main.java``` contains ```main``` method.

### 2. How to run :computer:

- Open this project in Eclipse IDE or anything else that can run Java code. But I recommend using Eclipse IDE because I used it when I coded this project, so maybe you can avoid some errors.

- This project uses ```JavaSE 1.8``` (Java 8), so you should config it in Java Build Path.

- Finally, you just need to run ```Main.java``` file.

### 3. More information :bulb:

- For demo, I have generated 100 3072-bit prime numbers (you can find them in ```output``` folder). E.g:

```

4280552890400010171405548124751337944263320743000462248134960105557274647467759999447089591524779812651719046636166172519524787941043803283503676469121013637906560467607826021092059856127138328570298743033633445030958802020812842429259194454042978899482795626957043409439395222243347144580552099909308352202415520928014435468839377179491645851125306665649096710182546278234766480681000347097112087351929098363164752637458514308781073495664994449374965330109360299838199578351483235017269703988991836261226312034201085004843857799788582143479691772925968959383005363244767066032839358550289581679940443438953140868377679102247495677299393713227472970159758456799992059357115632241527788710067347868381331637472750904958754868728171985453262951663618032532457033162848697273723920539787255772825735780284492357436039237460347113740940617721771594565152492422742647331144994733866925515719729069815300775832595237773337234400263
```


- The average time to generate a 3072-bit prime number here is about 8s (I think it's quite slow).

- In this project, we need to implement an algorithm which determines whether a given number is prime. I have followed the instructions in the document given by my teacher. Firstly, we generate the first 400 primes, and check if the given number is divisible by any of them. Secondly, we use Fermat's little theorem. Finally, we use Rabin - Miller algorithm. 

- I have found a online primality test, you can try it: https://kevincobain2000.github.io/miller-rabin-primality-test-online/



