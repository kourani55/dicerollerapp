package com.example.dice_roller_app

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.dice_roller_app.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var score = 0
        var score2 = 0
        binding.playerLabel.text = ("Player 1")
        binding.currentPlayerLabel.text = ("Current Turn: ")
        var currentJackpot = 5
        var currentTurn = true
        var isDouble = false


        //set button event handler
        binding.btnRollDie.setOnClickListener {
            var diceValue = Random.nextInt(1, 7)

            var imageName = "@drawable/dice" + diceValue

            var resourceID = resources.getIdentifier(imageName, "drawable", getPackageName())



            binding.ivDie.setImageResource(resourceID)


            //random int for values
            var addSubRndm1 = Random.nextInt(0, 99)
            var addSubRndm2 = Random.nextInt(0, 99)
            var multiplyRndm1 = Random.nextInt(0, 20)
            var multiplyRndm2 = Random.nextInt(0, 20)


            //when statement when rolling from 1 to 6 on the die
            when (diceValue) {


                //rolls 1 for random addition question
                1 -> {


                    //disables button roll until a guess has been made
                    binding.btnRollDie.isEnabled = false
                    binding.guessButton.isEnabled = true

                    //sets equation to a string to view
                    binding.equationLabel.text = ("$addSubRndm1 + $addSubRndm2 = ").toString()

                    //answer to equation
                    var answer = addSubRndm2 + addSubRndm1


                    //pressing the guess button allows user to roll again
                    binding.guessButton.setOnClickListener {

                        binding.btnRollDie.isEnabled = true

                        //checks if the answer is correct by comparing to the input
                        if (binding.userInputLabel.text.toString() == answer.toString()) {

                            //blanks text field
                            binding.userInputLabel.setText("")

                            //switches turns, also holds value for if the user rolls a 4 to
                            //get double the points when correct
                            if (currentTurn) {
                                binding.playerLabel.text = ("Player 2")
                                if (isDouble) {
                                    score += 1 * 2
                                    isDouble = false
                                } else {
                                    score += 1
                                }

                                //disables the guess button
                                binding.guessButton.isEnabled = false

                                //adds player1 score to the text view
                                binding.player1TotalAmount.text = score.toString()

                                //player turn becomes false
                                currentTurn = false

                                //switches player after adding points to the current player
                                //if question is answered correctly
                            } else {
                                binding.playerLabel.text = ("Player 1")

                                if (isDouble) {
                                    score2 += 1 * 2
                                    isDouble = false
                                } else {
                                    score2 += 1
                                }

                                //sets text view equal to score and makes turn true for next player
                                binding.player2TotalAmount.text = score2.toString()
                                currentTurn = true

                                //disables guess button until a roll is made
                                binding.guessButton.isEnabled = false


                            }

                            //if player answers wrong, adds amount to jackpot
                        } else {
                            binding.userInputLabel.setText("")

                            if (currentTurn) {
                                binding.playerLabel.text = ("Player 2")
                                currentTurn = false

                                binding.guessButton.isEnabled = false


                            } else {
                                binding.playerLabel.text = ("Player 1")
                                currentTurn = true

                                binding.guessButton.isEnabled = false

                            }

                            currentJackpot += 1
                            binding.jackpotAmountLabel.text = currentJackpot.toString()

                        }

                        //game over if either player reaches 20
                        if (score >= 20) {
                            binding.currentPlayerLabel.text = "GAME OVER. PLAYER 1 WINS"
                            binding.playerLabel.text = ""
                        } else if (score2 >= 20) {
                            binding.currentPlayerLabel.text = "GAME OVER. PLAYER 2 WINS"
                            binding.playerLabel.text = ""
                        }

                    }


                }

                //rolls 2 for random subtraction question
                2 -> {

                    //disables until a guess is made after first roll
                    binding.btnRollDie.isEnabled = false
                    binding.guessButton.isEnabled = true

                    //stores answer
                    binding.equationLabel.text = ("$addSubRndm1 - $addSubRndm2 = ").toString()
                    var answer = addSubRndm1 - addSubRndm2


                    binding.guessButton.setOnClickListener {

                        binding.btnRollDie.isEnabled = true

                        if (binding.userInputLabel.text.toString() == answer.toString()) {

                            binding.userInputLabel.setText("")

                            if (currentTurn) {
                                binding.playerLabel.text = ("Player 2")

                                //switches turns, also holds value for if the user rolls a 4 to
                                //get double the points when correct
                                if (isDouble) {
                                    score += 2 * 2
                                    isDouble = false
                                } else {
                                    score += 2
                                }
                                binding.player1TotalAmount.text = score.toString()
                                currentTurn = false

                                binding.guessButton.isEnabled = false

                            } else {
                                binding.playerLabel.text = ("Player 1")

                                if (isDouble) {
                                    score2 += 2 * 2
                                    isDouble = false
                                } else {
                                    score2 += 2
                                }
                                binding.player2TotalAmount.text = score2.toString()
                                currentTurn = true

                                binding.guessButton.isEnabled = false

                            }

                            //if player answers wrong, adds amount to jackpot
                        } else {

                            binding.userInputLabel.setText("")

                            if (currentTurn) {
                                binding.playerLabel.text = ("Player 2")
                                currentTurn = false

                                binding.guessButton.isEnabled = false

                            } else {
                                binding.playerLabel.text = ("Player 1")
                                currentTurn = true

                                binding.guessButton.isEnabled = false

                            }

                            currentJackpot += 2
                            binding.jackpotAmountLabel.text = currentJackpot.toString()

                        }

                        //game over if either player reaches 20
                        if (score >= 20) {
                            binding.currentPlayerLabel.text = "GAME OVER. PLAYER 1 WINS"
                            binding.playerLabel.text = ""
                        } else if (score2 >= 20) {
                            binding.currentPlayerLabel.text = "GAME OVER. PLAYER 2 WINS"
                            binding.playerLabel.text = ""
                        }
                    }


                }
                3 -> {
                    binding.btnRollDie.isEnabled = false
                    binding.guessButton.isEnabled = true
                    binding.equationLabel.text =
                        ("$multiplyRndm1 * $multiplyRndm2 = ").toString()
                    var answer = multiplyRndm1 * multiplyRndm2

                    binding.guessButton.setOnClickListener {

                        binding.btnRollDie.isEnabled = true

                        if (binding.userInputLabel.text.toString() == answer.toString()) {

                            binding.userInputLabel.setText("")

                            if (currentTurn) {
                                binding.playerLabel.text = ("Player 2")

                                if (isDouble) {
                                    score += 3 * 2
                                    isDouble = false
                                } else {
                                    score += 3
                                }

                                binding.player1TotalAmount.text = score.toString()
                                currentTurn = false

                                binding.guessButton.isEnabled = false


                            } else {
                                binding.playerLabel.text = ("Player 1")

                                //switches turns, also holds value for if the user rolls a 4 to
                                //get double the points when correct
                                if (isDouble) {
                                    score2 += 3 * 2
                                    isDouble = false
                                } else {
                                    score2 += 3
                                }
                                binding.player2TotalAmount.text = score2.toString()
                                currentTurn = true

                                binding.guessButton.isEnabled = false
                            }


                        } else {

                            binding.userInputLabel.setText("")

                            if (currentTurn) {
                                binding.playerLabel.text = ("Player 2")
                                currentTurn = false

                                binding.guessButton.isEnabled = false
                            } else {
                                binding.playerLabel.text = ("Player 1")
                                currentTurn = true

                                binding.guessButton.isEnabled = false

                            }

                            currentJackpot += 3
                            binding.jackpotAmountLabel.text = currentJackpot.toString()
                        }

                    }

                    //game over if either player reaches 20
                    if (score >= 20) {
                        binding.currentPlayerLabel.text = "GAME OVER. PLAYER 1 WINS"
                        binding.playerLabel.text = ""
                    } else if (score2 >= 20) {
                        binding.currentPlayerLabel.text = "GAME OVER. PLAYER 2 WINS"
                        binding.playerLabel.text = ""
                    }

                }

                //rolls 4 and has to roll again
                //isDouble true when a four is rolled, double points if next
                //question is correct
                4 -> {

                    //
                    binding.equationLabel.text = ("ROLL AGAIN").toString()
                    isDouble = true


                    if (score >= 20) {
                        binding.currentPlayerLabel.text = "GAME OVER. PLAYER 1 WINS"
                        binding.playerLabel.text = ""
                    } else if (score2 >= 20) {
                        binding.currentPlayerLabel.text = "GAME OVER. PLAYER 2 WINS"
                        binding.playerLabel.text = ""
                    }
                }
                //rolls 5 and loses turn
                5 -> {
                    
                    binding.equationLabel.text = ("LOSE A TURN").toString()

                    //switches to next player if current player rolls a 5
                    if (currentTurn) {
                        currentTurn = !currentTurn // switch turns
                        binding.playerLabel.text = if (currentTurn) "Player 1" else "Player 2"
                    } else {
                        currentTurn = true
                        binding.playerLabel.text = if (currentTurn) "Player 1" else "Player 2"
                    }




                    isDouble = false
                }

                //roll 6 to try for jackpot for 1-3
                //adds jackpot to player score if correct
                //does nothing if incorrect
                6 -> {

                    binding.equationLabel.text = ("JACKPOT QUESTION: ").toString()

                    var jackpotQuestion = Random.nextInt(1, 4)

                    when (jackpotQuestion) {


                        1 -> {

                            binding.btnRollDie.isEnabled = false
                            binding.guessButton.isEnabled = true

                            binding.equationLabel.text =
                                ("$addSubRndm1 + $addSubRndm2 = ").toString()
                            var answer = addSubRndm2 + addSubRndm1


                            binding.guessButton.setOnClickListener {

                                binding.btnRollDie.isEnabled = true
                                if (binding.userInputLabel.text.toString() == answer.toString()) {
                                    binding.userInputLabel.setText("")

                                    if (currentTurn) {
                                        binding.playerLabel.text = ("Player 2")
                                        if (isDouble) {
                                            score += currentJackpot
                                            isDouble = false
                                        } else {
                                            score += currentJackpot
                                        }

                                        currentJackpot = 5
                                        binding.jackpotAmountLabel.text = currentJackpot.toString()

                                        binding.guessButton.isEnabled = false


                                        binding.player1TotalAmount.text = score.toString()
                                        currentTurn = false

                                    } else {
                                        binding.playerLabel.text = ("Player 1")

                                        if (isDouble) {
                                            score2 += currentJackpot
                                            isDouble = false
                                        } else {
                                            score2 += currentJackpot

                                        }


                                        currentJackpot = 5
                                        binding.jackpotAmountLabel.text = currentJackpot.toString()

                                        binding.player2TotalAmount.text = score2.toString()
                                        currentTurn = true

                                        binding.guessButton.isEnabled = false

                                    }
                                } else {
                                    binding.userInputLabel.setText("")

                                    if (currentTurn) {
                                        binding.playerLabel.text = ("Player 2")
                                        currentTurn = false

                                        binding.guessButton.isEnabled = false

                                    } else {
                                        binding.playerLabel.text = ("Player 1")
                                        currentTurn = true

                                        binding.guessButton.isEnabled = false

                                    }


                                }

                                if (score >= 20) {
                                    binding.currentPlayerLabel.text = "GAME OVER. PLAYER 1 WINS"
                                    binding.playerLabel.text = ""
                                } else if (score2 >= 20) {
                                    binding.currentPlayerLabel.text = "GAME OVER. PLAYER 2 WINS"
                                    binding.playerLabel.text = ""
                                }


                            }



                        }

                        2 -> {
                            binding.btnRollDie.isEnabled = false
                            binding.guessButton.isEnabled = true

                            binding.equationLabel.text =
                                ("$addSubRndm1 - $addSubRndm2 = ").toString()
                            var answer = addSubRndm1 - addSubRndm2

                            binding.guessButton.setOnClickListener {

                                binding.btnRollDie.isEnabled = true

                                if (binding.userInputLabel.text.toString() == answer.toString()) {


                                    binding.userInputLabel.setText("")

                                    if (currentTurn) {
                                        binding.playerLabel.text = ("Player 2")

                                        if (isDouble) {
                                            score += currentJackpot
                                            isDouble = false
                                        } else {
                                            score += currentJackpot
                                        }
                                        currentJackpot = 5
                                        binding.jackpotAmountLabel.text = currentJackpot.toString()

                                        binding.player1TotalAmount.text = score.toString()
                                        currentTurn = false

                                        binding.guessButton.isEnabled = false

                                    } else {
                                        binding.playerLabel.text = ("Player 1")

                                        if (isDouble) {
                                            score2 += currentJackpot
                                            isDouble = false
                                        } else {
                                            score2 += currentJackpot
                                        }
                                        currentJackpot = 5
                                        binding.jackpotAmountLabel.text = currentJackpot.toString()
                                        binding.player2TotalAmount.text = score2.toString()
                                        currentTurn = true

                                        binding.guessButton.isEnabled = false


                                    }

                                } else {

                                    binding.userInputLabel.setText("")

                                    if (currentTurn) {
                                        binding.playerLabel.text = ("Player 2")
                                        currentTurn = false

                                        binding.guessButton.isEnabled = false


                                    } else {
                                        binding.playerLabel.text = ("Player 1")
                                        currentTurn = true

                                        binding.guessButton.isEnabled = false


                                    }


                                }

                                if (score >= 20) {
                                    binding.currentPlayerLabel.text = "GAME OVER. PLAYER 1 WINS"
                                    binding.playerLabel.text = ""
                                } else if (score2 >= 20) {
                                    binding.currentPlayerLabel.text = "GAME OVER. PLAYER 2 WINS"
                                    binding.playerLabel.text = ""
                                }
                            }

                        }
                        3 -> {
                            binding.btnRollDie.isEnabled = false
                            binding.guessButton.isEnabled = true
                            binding.equationLabel.text =
                                ("$multiplyRndm1 * $multiplyRndm2 = ").toString()
                            var answer = multiplyRndm1 * multiplyRndm2

                            binding.guessButton.setOnClickListener {

                                binding.btnRollDie.isEnabled = true

                                if (binding.userInputLabel.text.toString() == answer.toString()) {

                                    binding.userInputLabel.setText("")

                                    if (currentTurn) {
                                        binding.playerLabel.text = ("Player 2")

                                        if (isDouble) {
                                            score += currentJackpot
                                            isDouble = false
                                        } else {
                                            score += currentJackpot
                                        }

                                        currentJackpot = 5
                                        binding.jackpotAmountLabel.text = currentJackpot.toString()

                                        binding.player1TotalAmount.text = score.toString()
                                        currentTurn = false

                                        binding.guessButton.isEnabled = false



                                    } else {
                                        binding.playerLabel.text = ("Player 1")

                                        if (isDouble) {
                                            score2 += currentJackpot
                                            isDouble = false
                                        } else {
                                            score2 += currentJackpot
                                        }
                                        currentJackpot = 5
                                        binding.jackpotAmountLabel.text = currentJackpot.toString()
                                        binding.player2TotalAmount.text = score2.toString()
                                        currentTurn = true


                                        binding.guessButton.isEnabled = false

                                    }


                                } else {

                                    binding.userInputLabel.setText("")

                                    if (currentTurn) {
                                        binding.playerLabel.text = ("Player 2")
                                        currentTurn = false

                                        binding.guessButton.isEnabled = false

                                    } else {
                                        binding.playerLabel.text = ("Player 1")
                                        currentTurn = true

                                        binding.guessButton.isEnabled = false

                                    }

                                }

                            }

                            if (score >= 20) {
                                binding.currentPlayerLabel.text = "GAME OVER. PLAYER 1 WINS"
                                binding.playerLabel.text = ""
                            } else if (score2 >= 20) {
                                binding.currentPlayerLabel.text = "GAME OVER. PLAYER 2 WINS"
                                binding.playerLabel.text = ""
                            }



                        }


                    }

                }//end

            }//onCreate end
        }
    }
}

