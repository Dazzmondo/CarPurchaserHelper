# Car Advice and Recommendation Tool

## Brief description of piece

Interactive program created with Java to help user calculate their HP, PCP, weekly fuel cost, monthly cost, and to recommend a car based on their lifestyle/input using a menu.

This was my first project/assignment. All code was manually handwritten with no AI or autocomplete whatsoever. I have combined the README and the separate `reflection.txt` where we were asked to assess our own work against the included marking rubric. The code, README, and `reflection.txt` are in their original form, except the formatting has been updated for markdown.

## Instructions

How to run the program and state how to use any features of it:

1. Compile and run `Driver.java`
2. Select relevant option from menu (1 for HP, 2 for PCP, 3 for fuel, 4 for monthly cost, 5 for car recommendation, 0 to exit)
3. Enter requested inputs to calculate HP, PCP, weekly fuel cost, monthly cost or to receive a car recommendation.
4. Press/input enter after receiving each response to return to the menu (you will be prompted to do so by the program).
5. Press 0 to exit the program

## Added Functionality

**Pause:** Originally program would immediately loop back to the menu, which meant the user had to scroll up past the menu in the terminal to check their answer. Added a new string asking the user to press enter to return to menu. Used `Scanner` to take the user's input (enter). Then returned to the menu.
See lines 59-73, 93-96, 110-113, 127-130 and 142-146 in `Driver` class.

**IgnoreCase:** Originally, the String options in the `recommendCarType` method were case-sensitive. If user entered "Luxury" instead of "luxury", they would be asked to select one of the words provided. They would then be brought back to the menu and have to enter 5 to start the car recommendation feature again. Using the `equalsIgnoreCase` method decreased chance of input error and removed the need to loop back to the menu if the user entered Luxury, instead of luxury.
See lines 86-95 in `CarPurchaserHelper` class.

**2 decimals:** Originally, the numbers output would return calculations of € to many decimal places. While technically more accurate, changing the format of the reply replicated the €0.00 format we are more familiar with. The decision was made to change this to make the program more user-friendly. Used method `String.format("%.2f", value)`.
See lines 56-57, 91, 108 and 125 in `Driver` class.

## Known bugs/problems

- If you enter a char or String for any of the requests in choices 1-4, the program will crash. This is because `Scanner` is asking for a double input.
- If you enter a non-integer at the menu prompt, the program will crash. This is because the menu is only designed to accept integers.
- PCP calculation fails once GMFV percentage goes above an unrealistic threshold. In example given in reflection, calculation fails after 98% GMFV.

## Sources

- <https://medium.com/@AlexanderObregon/how-to-pause-a-java-program-for-a-few-seconds-safely-and-correctly-7b49c71576f9>
- <https://medium.com/@ethannam/understanding-the-levenshtein-distance-equation-for-beginners-c4285a5604f0>
- <https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo>
- <https://stackoverflow.com/questions/2538787/how-to-print-a-float-with-2-decimal-places-in-java>

---

# Reflection

## Reflection Outline

- Where selection/iteration used
- Methods with parameters and return values
- How PCP/HP were understood and used
- Coding process and challenges
- Abandoned ideas
- Marking Rubric Assessment
- Conclusion

## Where selection/iteration used

Selection was used to control the program. `if` and `else if` statements were used in the `CarPurchaserHelper` class to create the `calculateHP` and `calculatePCP` methods. (see lines 15-27 and 41-52)

Selection was used in the driver class to take the user to the relevant feature/sub-menu based on their number input in the main menu. (See lines 45-160)

Iteration was used in the `calculateHP` and `pcpResultMonth` methods, using `for` loops. The initial Loop Control Variable was set at 0 in each case and updated the Loop Control Variable by 1 until the Loop Control Variable was no longer less than the total number of months, which terminated the loop after the correct number of months, based on the user's input. (See lines 21 and 47)

Iteration was used to loop the user back to the main menu, using a `while` loop. (See line 30)

The Loop Control Variable was set at 7 because it could not match one of the suggested inputs (1,2,3,4,5,0) in the menu, and 6 was avoided in case the user accidentally inputted 6 instead of 0, as this would follow a logical thought process of increasing numbers for each option. (See line 27)

Realised later the number chosen for initialisation of `choice` was arbitrary, because as long as it wasn't 0, the program would continue to work as intended. This LCV (`choice`) was updated with each menu input from the user.

Exit was assigned 0 because this was the number assigned to the while clause (`while choice != 0`). 0 exits the program and signals to call the `printGoodbyeMessage` method.

The loop ensured the user could enter several numbers, instead of just entering 1 number, before the program would terminate. This made the program more efficient and usable.

## Methods with parameters and return values

Unique methods with different parameters and return values were created in the `CarPurchaserHelper` class. These were then called in the `Driver` class.

| Method | Parameters | Returns |
|---|---|---|
| `calculateHP` (line 10) | 4 - `double carPrice`, `double deposit`, `double annualInterest`, `int termYears` | 1 value - `double` |
| `calculatePCP` (line 32) | 5 - `double carPrice`, `double deposit`, `double annualInterest`, `int termYears`, `double gmfvPerCent` | 1 value - `double` |
| `calculateMonthlyCost` (line 70) | 2 - `double carPrice`, `int numMonths` | 1 value - `double` |
| `fuelCostEstimator` (line 77) | 2 - `double kmPerWeek`, `double fuelCost` | 1 value - `double` |
| `recommendCarType` (line 84) | 1 - `String lifestyle` | 1 value - `String` |
| `printWelcomeMessage` (line 58) | 0 | 1 value - `String` |
| `printGoodbyeMessage` (line 64) | 0 | 1 value - `String` |

Defining the methods in the `CarPurchaserHelper` class and calling them in the `Driver` class kept the code simpler and more readable. If they had needed to be called multiple times in the `Driver` class, this would have removed the need to individually type the code again and again. For longer programs, being able to reuse these methods is useful.

## How PCP/HP were understood and used

HP and PCP definitions were given on the Tutors Assignment webpage. The code to create their calculation methods was also given. These were called in the `Driver` class to calculate the user's HP and PCP based on the parameters they input.

**Hire Purchase (HP)** is a finance plan where you pay a deposit and then equal monthly payments until the full amount is paid. You own the car once the final payment is made.

**Personal Contract Plan (PCP)** is a type of car finance where you pay a deposit, followed by monthly payments over a fixed term. At the end, you can either return the car, pay a balloon payment to own it, or trade it in.

The PCP benefit is needing to pay lower monthly installments, but you need to pay more at the end if you intend to keep the car with a balloon payment. PCP is also impacted by the Guaranteed Minimum Future Value of the car measured in % of current value. PCP would be a logical plan for someone who doesn't intend to keep the car, as they could benefit from lower costs per month.

The HP cost is spread evenly throughout the term, and you own the car at the end. HP might make more sense for those who wish to own the car at the end of their term.

An example input for each using the program:

| Plan | Price | Deposit | Interest | Term | Monthly Payment |
|---|---|---|---|---|---|
| HP | €20000 | 400 | 6% | 4 yrs | €460.31 |
| PCP GMFV (40%) | €20000 | 400 | 6% | 4 yrs | €272.43 |
| PCP GMFV (80%) | €20000 | 400 | 6% | 4 yrs | €84.54 |
| PCP GMFV (90%) | €20000 | 400 | 6% | 4 yrs | €37.57 |
| PCP GMFV (98%) | €20000 | 400 | 6% | 4 yrs | €0.00 |

Formula stops working at 98% and while this is an unrealistic GMVF that would never exist in real-life scenarios, it could be considered a bug. See below results become negative numbers:

| Plan | Price | Deposit | Interest | Term | Monthly Payment |
|---|---|---|---|---|---|
| PCP GMFV (99%) | €20000 | 400 | 6% | 4 yrs | €-4.69 |
| PCP GMFV (100%) | €20000 | 400 | 6% | 4 yrs | €-9.39 |

## Coding process and challenges

I intentionally avoided any outside research during the early stages of the coding process. This was because I wanted to get the program to compile only using the material we had been taught in lectures and labs, along with the pre-given code. This was on the understanding that the assignment would only have requirements based on what we had already learned.

I began first by coding the `CarPurchaserHelper` class. The HP and PCP methods were already coded. These were based on the given explanations for how each of these were calculated in the Tutors Assignment description page.

I created the `printWelcomeMessage` and `printGoodbyeMessage` methods by returning a simple String. There were no special parameters, as these were just general welcome and goodbye messages.

I created the `calculateMonthlyCost` method by dividing the car price by the number of months.

I created the `fuelCostEstimator` method by dividing the `kmPerWeek` parameter by 100, then multiplying by 6 (representing litres), and then multiplying that by the `fuelCost` parameter in accordance with the assignment brief.

I actually delayed creating the `recommendCarType` method, because I was unsure if I wanted to create this using several `if` and `else if` statements, or if I wanted to use arrays to demonstrate understanding of the concept. Thus, I began coding the `Driver` class next and testing if options 1-4 worked.

I followed the instructions, creating the `carPurchaserHelper` object. I called the `printWelcomeMessage` method. I then created the menu in the `while` loop. I then created each `if`/`else if` statement for each feature of the menu, calling each corresponding method. I used `Scanner` to take each of these inputs. I called the `printGoodbyeMessage` method when the user entered 0.

I took the `int choice = input.nextInt()` and put it into the `while` loop, because I found there was no prompt for the user to input anything before the menu was printed. I recognised the need to create a Loop Control Variable.

I was confused by the instruction about updating the Loop Control Variable. I understood what this meant, as it was covered in week 2 of lectures. However, in those examples the Loop Control Variable was incrementing with each loop, which determined when it should terminate. In this case though, it didn't make sense to increment the loop control variable, because the menu was not supposed to terminate after a pre-determined number of loops. Instead, it was up to the user to decide when to terminate the program by entering 0 to exit.

I was unsure if this was a mistake in the briefing or if I misunderstood how the exit condition was supposed to work for this program, but given the example menu had an Exit option in its menu, I believed mine was the correct implementation, and there was no need to update the Loop Control Variable of 7 with each iteration.

I realised after checking the code again, that the `choice` LCV was actually being updated each time the user entered a new number on the menu prompt. Once they pressed 1, the LCV changed from 7 to 1. I simply misunderstood the instruction, because I associated the LCV update with being a systematic incrementation, e.g. `i++`. Once the user updated the LCV to 0, the program would terminate.

I then tested the program. It didn't compile at first, because I had accidentally placed a `;` before the `{` in one of the `else if` clauses. After this, I tried again and it did compile. I tested each of the features except the car recommender.

I realised that once the user entered the required parameters and the program returned the answer, it would immediately display the menu options. This meant the user needed to scroll up past the menu to get their answer which was not convenient. I researched how to insert a pause to let the user digest the answer before being directed back to the menu. I came up with a pause feature, which would ask the user to press enter to return to the menu.

However, this did not work at first, as even after entering a `println` asking the user to press enter to go back to the menu, and subsequently adding `input.nextLine()` in the following line, it was still circling back to the menu immediately. I discovered this was because when a user enters an input following `input.nextInt()`, it actually leaves a `"\n"` on the following line. Entering just one `input.nextLine()` reads `"\n"` immediately and instantly returns to the menu as before. To address this, I entered `input.nextLine()` a 2nd time which paused and gave the user time to press enter. I then repeated this for each feature.

I went back to the `CarPurchaserHelper` class to complete the `recommendCarType` method. I decide to just use `else if` statements instead of arrays to keep it simpler. We hadn't really learned about 2D arrays yet, which was one of the main factors in deciding not to use them for this project. I integrated the `recommendCarType` method into the `Driver` class by calling it when the user input 5.

The program was now compiling correctly and in a functional state, meeting all the required criteria. However, there wasn't much added functionality, so I decided to add in an aesthetic feature by making the program outputs resemble the €0.00 format instead of €0.000000000 for example. I researched how to do this and found a method to do this. I used the method `String.format("%.2f", value)` for each of the numeric outputs.

I then created the ReadMe file. Finally, I began writing this reflection.

## Abandoned ideas

Considered implementing a way for the program to store HP and PCP in memory, and then suggest which of the two options would be best for the user based on their previous inputs. It would have been accessed by the user by inputting 3 after already inputting 1 and 2 in the menu. Abandoned because it was complicated to implement, required code not yet covered in lectures or labs, and didn't add much to the user experience.

Considered entering an auto-correct feature to deal with mistyped Strings on the car recommendation screen. In the current program, if you enter "adventure" instead of "adventurer", the program will inform you this is not a valid option and ask you to enter a valid option. You are then returned to the menu and have to press 5 to start this process again. Designing a feature where the program would understand the user likely intended to input "adventurer" when they accidentally entered "adventure" would make the program more efficient, and reduce the time needed to get a recommendation.

Abandoned because there was no ready-made method for this feature, which meant I would have needed to design an algorithm to do this from scratch. I researched how it could be done and did find a way to implement this type of method. However, it was complicated and based on content not yet covered in labs or lectures. Despite this, it was the last feature I decided to cut, because I did think this added benefit to the user, and thought an understanding of how to create this method could have long-term benefits in the future.

The reason I ultimately decided to remove this feature was that I had no prior knowledge of how this code was designed before researching it, and I feared I might not be able to explain it properly in an interview. A more basic auto-correct feature was implemented using the pre-existing method `equalsIgnoreCase` to deal with any accidental capitalisation, which would have otherwise demanded the user return to the menu and enter option 5 again.

If interested, the proposed method is called the Levenshtein Distance Equation. You can read more on it here: <https://medium.com/@ethannam/understanding-the-levenshtein-distance-equation-for-beginners-c4285a5604f0>

I also considered prompting the user to input their budget, and recommend 1 high-end and 1 low-end option for each car recommendation. Decided against this for simplicity. There also weren't many speed demon or luxury options available to the common people :/ .

## Marking Rubric Assessment

### Part A - Comments/Indention etc (9 / 9)

| Criterion | Score |
|---|---|
| comments | 1 / 1 |
| formatting | 2 / 2 |
| naming | 2 / 2 |
| structure of code | 2 / 2 |
| inclusion of readme and reflection | 2 / 2 |

### Part B - Working Project (6 / 6)

| Criterion | Score |
|---|---|
| Code compiles and runs without error | 6 / 6 |

### Part C - CarPurchaserHelper (34 / 34)

| Criterion | Score |
|---|---|
| PCP method explained | 7 / 7 |
| HP method explained | 7 / 7 |
| All other methods written | 20 / 20 |

### Part D - Driver (36 / 36)

| Criterion | Score |
|---|---|
| carPurchaserHelper object created | 3 / 3 |
| Code written as per spec | 33 / 33 |

Code written as per spec breakdown:

| Criterion | Score |
|---|---|
| HP Calculation code uncommented and working | 3 / 3 |
| PCP code correctly written and outputs amount | 15 / 15 |
| lcv correctly updating and menu looping | 5 / 5 |
| all methods created in CarPurchaserHelper called and used correctly | 10 / 10 |

### Part E - Reflection/Readme (10 / 10)

| Criterion | Score |
|---|---|
| Filled out parts A - D above | 3 / 3 |
| Completed readme as per spec | 2 / 2 |

### Part F - Final Product (5 / 5)

| Criterion | Score |
|---|---|
| Well written menu and ease of use | 5 / 5 |

### Part G - For Extra Credit (10%) (5 / 10)

Added Functionality:

**Pause:** Originally program would immediately loop back to the menu, which meant the user had to scroll up past the menu in the terminal to check their answer. Added a new string asking the user to press enter to return to menu. Used `Scanner` to take the user's input (enter). Then returned to the menu.
See lines 59-73, 93-96, 110-113, 127-130 and 142-146 in `Driver` class.

**IgnoreCase:** Originally, the String options in the `recommendCarType` method were case-sensitive. If user entered "Luxury" instead of "luxury", they would be asked to select one of the words provided. They would then be brought back to the menu and have to enter 5 to start the car recommendation feature again. Using the `equalsIgnoreCase` method decreased chance of input error and removed the need to loop back to the menu if the user entered Luxury, instead of luxury.
See lines 86-95 in `CarPurchaserHelper` class.

**2 decimals:** Originally, the numbers output would return calculations of € to many decimal places. While technically more accurate, changing the format of the reply replicated the €0.00 format we are more familiar with. The decision was made to change this to make the program more user-friendly. Used method `String.format("%.2f", value)`.
See lines 56-57, 91, 108 and 125 in `Driver` class.

### Sources

- <https://medium.com/@AlexanderObregon/how-to-pause-a-java-program-for-a-few-seconds-safely-and-correctly-7b49c71576f9>
- <https://medium.com/@ethannam/understanding-the-levenshtein-distance-equation-for-beginners-c4285a5604f0>
- <https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo>
- <https://stackoverflow.com/questions/2538787/how-to-print-a-float-with-2-decimal-places-in-java>

## Conclusion

The most difficult part of this assignment was actually the reflection. It took me a long time to finish and at times I found it difficult to remember the exact process and order of the challenges I faced. I used comprehensive notes throughout my code, and this helped guide me in my reflection. Had I not added so many notes to my code I would have struggled to write this reflection, because I began writing it several days after I had finished my code.

I also struggled with assessing my own work. Giving myself maximum marks made me feel uncomfortable, and I didn't feel I could truly be completely objective. I gave myself maximum marks for the required criteria, because I felt my program did achieve these. However, the extra credit/functionality marking seemed a bit more vague. I didn't know how many marks to award for each added feature. I ultimately scrapped my more ambitious ideas, so I scored my extra credit on the lower end, even though I do think the pause feature I implemented improves the program. I wasn't sure whether adding `equalsIgnoreCase` would count as an extra feature, as, though it wasn't in the brief, it does seem like a rather basic addition, and is something that was referenced in lectures. I added the 2 decimal point feature which was a minor aesthetic upgrade (though you could argue it came at the expense of accuracy).

There is a bug if you enter a char or String for any of the requests in choices 1-4. This will cause the program to stop working. This is because `Scanner` is asking for a double input.

If you enter a non-integer at the menu prompt, the program will crash. This is because the menu is only designed to accept integers.

There may be a bug in the PCP calculation when the user enters an unrealistic GMFV percentage, see example where 99% returned a negative number. However, for any realistic, non-theoretical input, this method will still return an accurate output.

While not a bug, there is a minor inconvenience in needing to go back to the menu and re-press option 5 if you misspell one of the lifestyle options.

No other known bugs.

The assignment was very useful for getting practice with loops. The menu is a feature that I will undoubtedly use again in the future. It was also useful to get practice defining methods in one class, and then calling them in the `Driver` class - a good practice for future projects. Actually compiling the program helped me discover things I wouldn't have thought about. For example, when typing the original code, I did not realise it would immediately loop back to the menu before the user could actually read the program's output in each category. Now I know adding a buffer message will be good practice in the future to improve the user experience.

The assignment also gave me appreciation for my ability to conceptualise solutions to problems without necessarily knowing how to implement them perfectly in code yet. For example, having an auto-correct method would improve user experience in cases where they mistype a pre-set option. Their need to go back to the menu, and then restart choice 5, was not optimal. I also thought of the utility of saving inputs into memory. In this particular assignment, I didn't consider the ability to save HP and PCP data, and recommending which to choose, to be particularly useful, given there were only 2 basic numbers the user needed to remember. However, I can see it being useful for future cases where there are more options to remember, or more parameters to determine a selection.

I was unsure of what to write in the reflection about the HP and PCP calculations. We were given the calculations and the definitions. The definitions explained how they were calculated and the code implemented the corresponding formulas. They were important to making sure the program outputted the correct result for the user. All this seems pretty self-evident though, so I'm wondering if there was something else I was supposed to say about them that I didn't think of.

Ultimately, I was unsure how long and detailed the reflection needed to be, though I feel this was probably on the longer side, and it should ideally be more brief. I'd welcome any input on how many words I should realistically be aiming for in future reflections. I recognise the value of the reflection in helping me to explain my thought process. In my future career, I will need to be able to explain my logic and ideas in a coherent story in interviews and in cases where my code needed to be documented and explained for others.