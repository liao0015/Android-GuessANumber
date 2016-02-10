/**
 * {Guess a Number Game}
 *
 * @author: BO LIAO {liao0015@algonquinlive.com}
 */


package com.algonquincollege.liao0015.guessanumber;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int newRanNum;
    int guessCount;
    String newString = null;

    //method to check if the user input is an integer
    public static boolean isInteger(String str){
        if(str == null){
            return false;
        }
        int length = str.length();
        if (length == 0){
            return false;
        }
        int i = 0;
        //if the first character entered is space, ignore it
        if(str.charAt(0) == ' '){
            if(length == 1){
                return false;
            }
            i = 1;
        }
        //reset the index and check the rest of the characters
        for (i = i; i < length; i++){
            char c = str.charAt(i);
            if(c < '0' || c > '9'){
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //display a random AI image
        ImageView ai1 = (ImageView) findViewById(R.id.aiImage);
        ImageView ai2 = (ImageView) findViewById(R.id.aiImage2);
        ImageView ai3 = (ImageView) findViewById(R.id.aiImage3);
        ImageView ai4 = (ImageView) findViewById(R.id.aiImage4);
        ImageView ai5 = (ImageView) findViewById(R.id.aiImage5);
        ImageView ai6 = (ImageView) findViewById(R.id.aiImage6);


        ai1.setVisibility(View.INVISIBLE);
        ai2.setVisibility(View.INVISIBLE);
        ai3.setVisibility(View.INVISIBLE);
        ai4.setVisibility(View.INVISIBLE);
        ai5.setVisibility(View.INVISIBLE);
        ai6.setVisibility(View.INVISIBLE);


        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Share your score with friends", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        Button resetButton = (Button) findViewById(R.id.resetBtn);

        //normal click the Start Game button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upon clicking the start button, the following actions will be taken
                //enable user input
                EditText userInput = (EditText) findViewById(R.id.userInput);
                userInput.setEnabled(true);

                //generate a random number
                generateRandomNum g1 = new generateRandomNum();
                newRanNum = g1.logNum();
                guessCount = 0;
                newString = String.valueOf(newRanNum);

                //display the message
                TextView outputLog = (TextView) findViewById(R.id.output);
                outputLog.setText("A random number from 1 - 1000 is generated.");

                //reset the previous message
                TextView winLog = (TextView) findViewById(R.id.winMessage);
                winLog.setText("");


                //display a random AI image
                ImageView ai1 = (ImageView) findViewById(R.id.aiImage);
                ImageView ai2 = (ImageView) findViewById(R.id.aiImage2);
                ImageView ai3 = (ImageView) findViewById(R.id.aiImage3);
                ImageView ai4 = (ImageView) findViewById(R.id.aiImage4);
                ImageView ai5 = (ImageView) findViewById(R.id.aiImage5);
                ImageView ai6 = (ImageView) findViewById(R.id.aiImage6);

                ai1.setVisibility(View.INVISIBLE);
                ai2.setVisibility(View.INVISIBLE);
                ai3.setVisibility(View.INVISIBLE);
                ai4.setVisibility(View.INVISIBLE);
                ai5.setVisibility(View.INVISIBLE);
                ai6.setVisibility(View.INVISIBLE);

                //create an array stores the  AI images
                int imageIdArray[] = {ai1.getId(), ai2.getId(), ai3.getId(), ai4.getId(), ai5.getId(), ai6.getId()};

                generateRandomImageId r3 = new generateRandomImageId();
                int randomImageIdNumber = r3.logNum();

                if (imageIdArray[randomImageIdNumber] == R.id.aiImage) {
                    ai1.setVisibility(View.VISIBLE);
                } else if (imageIdArray[randomImageIdNumber] == R.id.aiImage2) {
                    ai2.setVisibility(View.VISIBLE);
                } else if (imageIdArray[randomImageIdNumber] == R.id.aiImage3) {
                    ai3.setVisibility(View.VISIBLE);
                } else if (imageIdArray[randomImageIdNumber] == R.id.aiImage4) {
                    ai4.setVisibility(View.VISIBLE);
                } else if (imageIdArray[randomImageIdNumber] == R.id.aiImage5) {
                    ai5.setVisibility(View.VISIBLE);
                } else if (imageIdArray[randomImageIdNumber] == R.id.aiImage6) {
                    ai6.setVisibility(View.VISIBLE);
                }

            }
        });

        //long click the Start game button
        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //long click will show the random number as toast message
                if(newString == null){
                    Toast.makeText(getApplicationContext(), "No random number was generated", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "The random Num: " + newRanNum, Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

        //Press the guess button to play the game
        Button guessButton = (Button) findViewById(R.id.guessBtn);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //acquire user guess input
                EditText userInput = (EditText) findViewById(R.id.userInput);
                String userGuessedNum = userInput.getText().toString();
                userInput.setText("");

                TextView outputLog = (TextView) findViewById(R.id.output);
                outputLog.setText("Click Start to start the game");

                TextView winLog = (TextView) findViewById(R.id.winMessage);
                winLog.setText("");

                //make sure a random number is generate before the user click the "guess" button
                if (newString == null) {
                    userInput.setError("Please click Start button to start");
                    userInput.requestFocus();
                    return;
                }

                //display a message when the game went for too long
                if (guessCount == 10) {
                    Toast.makeText(getApplicationContext(), "Click Start button to reset the game", Toast.LENGTH_LONG).show();
                }

                //guess the number
                if (userGuessedNum.isEmpty()) {
                    userInput.setError("Please enter a Number");
                    userInput.requestFocus();
                    outputLog.setText("Please enter a Number");
                    return;
                } else if (!isInteger(userGuessedNum)) {
                    userInput.setError("Please enter a valid integer");
                    userInput.requestFocus();
                    outputLog.setText("Please enter a valid integer");
                    return;
                } else if (Integer.parseInt(userGuessedNum) > 1000) {
                    userInput.setError("Please enter a integer less or equals to 1000");
                    userInput.requestFocus();
                    outputLog.setText("Please enter a integer less or equals to 1000");
                    return;
                } else if (Integer.parseInt(userGuessedNum) < 1) {
                    userInput.setError("Please enter a integer larger or equals to 1");
                    userInput.requestFocus();
                    outputLog.setText("Please enter a integer larger or equals to 1");
                    return;
                } else if ((newRanNum - Integer.parseInt(userGuessedNum)) > 0) {
                    if (Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) >= 600) {
                        guessCount += 1;
                        Toast.makeText(getApplicationContext(), "Way Too Small!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        outputLog.setText("Way too Small!  Total guesses: " + guessCount);
                    } else if (Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) >= 300 &&
                            Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) < 600) {
                        guessCount += 1;
                        Toast.makeText(getApplicationContext(), "Too Small!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        outputLog.setText("Too Small!  Total guesses: " + guessCount);
                    } else if (Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) > 0 &&
                            Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) < 300) {
                        guessCount += 1;
                        Toast.makeText(getApplicationContext(), "A Little Small!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        outputLog.setText("A Little Small!  Total guesses: " + guessCount);
                    }
                } else if ((newRanNum - Integer.parseInt(userGuessedNum)) < 0) {
                    if (Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) >= 600) {
                        guessCount += 1;
                        Toast.makeText(getApplicationContext(), "Way Too Large!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        outputLog.setText("Way too Large!  Total guesses: " + guessCount);
                    } else if (Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) >= 300 &&
                            Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) < 600) {
                        guessCount += 1;
                        Toast.makeText(getApplicationContext(), "Too Large!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        outputLog.setText("Too Large!  Total guesses: " + guessCount);
                    } else if (Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) > 0 &&
                            Math.abs(newRanNum - Integer.parseInt(userGuessedNum)) < 300) {
                        guessCount += 1;
                        Toast.makeText(getApplicationContext(), "A Little Large!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        outputLog.setText("A Little Large!  Total guesses: " + guessCount);
                    }
                } else if ((newRanNum - Integer.parseInt(userGuessedNum)) == 0) {


                    guessCount += 1;

                    if (guessCount <= 5) {
                        Toast.makeText(getApplicationContext(), "Superior Win!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        winLog.setText("Superior Win!  Total guesses: " + guessCount);
                    } else if (guessCount > 5 && guessCount <= 10) {
                        Toast.makeText(getApplicationContext(), "Excellent Win!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        winLog.setText("Excellent Win!  Total guesses: " + guessCount);
                    } else {
                        Toast.makeText(getApplicationContext(), "You Win (finally)!  Total guesses: " + guessCount, Toast.LENGTH_SHORT).show();
                        winLog.setText("You Win (finally)!  Total guesses: " + guessCount);
                    }
                    outputLog.setText("Press Start to start a New Game!");
                    guessCount = 0;
                    userInput.setEnabled(false);
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            DialogFragment bob = new AboutDialogBox();
            bob.show(getFragmentManager(), "foo");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
