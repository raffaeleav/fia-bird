<p align="center">
  <img src="https://github.com/raffaeleav/fia-bird/assets/114619463/2e8ea9ad-739f-4e58-98cb-70539385edf2" width ="512" heigth="120">
</p>

<p align="center">
  Una rivisitazione del gioco Flappy Bird con intelligenza artificiale sviluppata come progetto per l'insegnamento di Fondamenti di intelligenza artificiale, del corso di Laurea in Informatica dell'Università degli Studi di Salerno.
</p>

## Preview
![ezgif com-gif-maker](https://user-images.githubusercontent.com/16355437/216096523-34e72180-1b26-4527-8ccf-cd299a959f8b.gif)


##Autori
|Nome|Matricola|
|----|---------|
| Aviello Raffaele | 0512110529 |
| Menzione Michele | 0512109797 |


## Introduzione
Il progetto FIA Bird nasce con l’intento di sperimentare come l’intelligenza artificiale si può applicare ai videogiochi. Tra le varie possibili scelte nell’ambito videoludico è stato scelto il noto gioco Flappy Bird, utilizzando un’implementazione abbastanza simile al gioco originale, disponibile su GitHub: [Jaryt/FlappyBirdTutorial](https://github.com/Jaryt/FlappyBirdTutorial).<br>
Lo **scopo** del progetto è quello di creare un’ IA in grado di giocare a Flappy Bird in modo autonomo.
Il gioco consiste nel far passare l’agente (rappresentato dallo sprite di un robot) attraverso una serie di ostacoli rappresentati da tubi con l’obbiettivo di riuscire a far volare l’agente il più lontano possibile, senza urtarli.


## Dipendenze
Il progetto è stato creato con l'ausilio dell'IDE IntelliJ IDEA Ultimate, tutti i passaggi mostrati successivamente faranno riferimento ad esso.
- [JDK 19](https://www.oracle.com/java/technologies/downloads/#java19 "JDK 19")

## Guida alla configurazione
Da **IntelliJ IDEA Ultimate** seguire i seguenti passaggi: *File* > *New* > *Project From Version Control* nel campo *URL* inserire questo [link](https://github.com/raffaeleav/progetto-fia-fia-bird).
<br>
<br>
![image](https://user-images.githubusercontent.com/16355437/215322368-34426ef0-d153-4296-a851-926bfdfed0c0.png)
<br>

## Esecuzione dell'algoritmo
Dal package iaModule, si può scegliere quale algoritmo eseguire tra:
- Hill Climbing
- Hill Climbing Stocastico
