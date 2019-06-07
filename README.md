```$xslt
 ___      ___       __        ________  ___________  _______   _______   ___      ___   __    _____  ___   ________   
|"  \    /"  |     /""\      /"       )("     _   ")/"     "| /"      \ |"  \    /"  | |" \  (\"   \|"  \ |"      "\  
 \   \  //   |    /    \    (:   \___/  )__/  \\__/(: ______)|:        | \   \  //   | ||  | |.\\   \    |(.  ___  :) 
 /\\  \/.    |   /' /\  \    \___  \       \\_ /    \/    |  |_____/   ) /\\  \/.    | |:  | |: \.   \\  ||: \   ) || 
|: \.        |  //  __'  \    __/  \\      |.  |    // ___)_  //      / |: \.        | |.  | |.  \    \. |(| (___\ || 
|.  \    /:  | /   /  \\  \  /" \   :)     \:  |   (:      "||:  __   \ |.  \    /:  | /\  |\|    \    \ ||:       :) 
|___|\__/|___|(___/    \___)(_______/       \__|    \_______)|__|  \___)|___|\__/|___|(__\_|_)\___|\____\)(________/  

```

### Introduction
This small project was inspired by the Leeds Code Dojo (https://www.meetup.com/Leeds-Code-Dojo/) and the most recent 
event which was the Mastermind Coding Kata evening.

The goal was of the evening was simple:
- Have fun
- Pair Program
- Learn something new!
- Create some logic that simulated the game Mastermind

My original attempt for this challenge on the evening can also be found on github, which was a collaboration between myself, Zahir Ahmed & Sep Russell. (https://github.com/james-millner/Mastermind)

After the event I thought I'd spend a little more time on the challenge and do some refactoring, generally playing around with some more code!

This lead me to develop a solution in Micronaut, as an intended CLI tool to play Mastermind. 

For reference see:

- Micronaut (https://micronaut.io)
- Micronaut CLI (https://docs.micronaut.io/1.2.0.RC1/guide/index.html#picocliGenerateProject)

## How to play:

There is a make file for building and running the application. 

Run the following commands:
1. `make build-and-run` - this will clean, package and run the application

For further commands available please consult the Makefile, in the root of this repository.
