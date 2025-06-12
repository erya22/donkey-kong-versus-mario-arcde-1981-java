# DK vs Mario Multiplayer (Java Arcade Game)

Un videogioco multiplayer ispirato alla versione arcade di *Donkey Kong vs Mario* del 1981, sviluppato in Java con architettura MVC (Model-View-Controller) e supporto client-server.

## 🎮 Descrizione

Il progetto è un gioco arcade multiplayer che riprende lo stile e la dinamica dell'originale Donkey Kong del 1981. È stato progettato per due giocatori in rete, uno nel ruolo di Mario e uno in quello di Luigi, con l'obiettivo di offrire un'esperienza competitiva ma fedele all'estetica retro originale.

## 🧠 Obiettivi Tecnici

- Applicare l'architettura **MVC** in un contesto di game development.
- Implementare la comunicazione **Client-Server** via TCP.
- Gestire **entità di gioco**, **collisioni**, **input da tastiera** e **aggiornamento dello stato** del gioco.
- Separare i menu e le scene del gioco in componenti modulari.

---

## 📁 Architettura del Progetto (MVC)

### 🗂️ **Model**
Contiene tutte le classi che rappresentano lo stato del mondo di gioco e la logica associata alle entità.

- **Entità principali**: `Player`, `DK`, `Barrel`, `Hammer`, `Pauline`, `Scala`, `Trave`
- **Classi generiche**: `Entity` (superclasse), `GameItem` (superclasse per oggetti)
- **Ambiente e mappa**: `TileMap`, `Layer`, `Terrain`, `Universe`
- **Gestione movimento e stati**: `MovementState`, `State`
- **Altro**: `Tile`, `Barrel`, ecc.

### 🗂️ **View**
Gestisce la rappresentazione grafica del mondo di gioco e delle entità.

- `GameWindow`: finestra principale del gioco
- `MapRenderer`: rendering della mappa e livelli
- `UniverseView`: vista generale del mondo
- `PlayerView`, `DKView`: vista dei personaggi

### 🗂️ **Controller**
Responsabile della logica del gioco, gestione input e aggiornamento dello stato.

- `GameEngine`: ciclo di gioco principale
- `PlayerController`, `DKController`, `BarrelController`, `HammerController`: controllano il comportamento delle entità
- `UniverseController`: coordina gli eventi globali

> ⚠️ I controller gestiscono gli input utente, il ciclo di aggiornamento (`update`) e invocano le modifiche del modello.

### 🗂️ **Net**
Gestisce la comunicazione multiplayer client-server.

- `DKServer`: server TCP
- `DKVsMario`: client TCP che connette al server

### 🗂️ **Utils**
Funzioni di supporto e caricamento dati:

- `TileMapLoader`: carica la mappa da file (es. JSON da Tiled)
- `MapParser`: parsing dei dati della mappa
- `TileUtils`: funzioni di supporto per tile
- `GameSettings` (da aggiungere): conterrà costanti e variabili iniziali (es. dimensioni schermo, tile, ecc.)

### 🗂️ **Menu/UI**
Gestione dell'interfaccia utente e menu:

- `GameFrame`, `GameWindow`
- `GameLauncher`, `GameMenu`, `ModeSelectionMenu`
- `GameResultDialogue`: pe
