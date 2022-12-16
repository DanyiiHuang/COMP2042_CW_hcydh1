# COMP2042_CW_hcydh1
<h1 align="center"> Hi👋 Welcome to my 2048 game!</h1>
<h3 align="center"> Quick Tour to the game</h3>
  
---

## Student Information
Student Name: Huang Danyi

Student ID: 20411372

## How to Compile the code to produce the application
1. Download Coursework Description and ZIP file from Moodle.

2. Save it to local computer under Desk D java File.

3. Import the unziped folder into IntelliJ.

4. Download required JavaFX library.

5. Run Main.java on Java version 17.0.

## Javadoc documentation location


## List of features that work properly
**1. Game Scene**
- menu page has "Welcome to 2048 Games" Title
- menu page has choice of **"Color Scheme"**, can choose one of the two color themes.
- menu page has choice of **"Game Mode"**, can choose **"common mode"** or **"challenge mode"**.
- menu page has choice of **"Challenge Mode"**, can choose different countdown timer.
- menu page has **"Start Game"** button, after select all choices above can start game.
- menu page has **"Exit Game"** button, the game end and terminate.

**2. Game mode**

2.1 Common mode
- Shows Scoring of the game
- Shows Highest Score
- Refresh the highest score in the next round of game until new highest score is achieved
- Shows current chosen mode

2.2 Challenge mode
- have two different choice: **180s** or **60** countdown timer.
- Shows Scoring of the game
- Shows current chosen mode

## List of Features that don't work properly
- Movement of number Block
- Multi-Account player mode
- Challenge Mode doesn't have leaderboard
- Challenge Mode doesn't refresh the highest score since it does not save highest score.
- Cannot create multi-account

## Reason of not working features
- No multi-account because time limited
- When reach 1024 the word will exceed cell frame(haven't found out where is this bug)

## List of new Java Class added
- Controller.java
- View.java
- MyState.Java is introduced to add monitor to the window to prevent code running while countdown number should terminate.
- EndGame

## List of Java Class modified
- Main.java
- Account.java
- Cell.java
- Game.java
- TextMaker.java