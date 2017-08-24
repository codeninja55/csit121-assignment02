# CSIT121/CSIT821 - Object Oriented Design and Programming

## Assignment 2 (due in week 8 & 9)
The purpose of this assignment is to practice building a GUI based program in Java and to use HashMaps. This assignment continues the scenario from assignment 1.


### Standard Level
Your program will provide a swing based GUI that allows a user to:
1. Register a new card (anon, basic or premium)
2. View the list of cards
3. Make a purchase
4. Show a summary

The program user will be able to issue new customers with a card. When a customer registers a new card they will need to specify the type of card via radio buttons or a drop-down-box and they will be issued with a card ID.

The program user will be able to view the list of all cards in the order they were created or in alpha-numeric order. A TextArea will be used to show this list.

The program user will be able to find then print into the TextArea the details of a single card (it points, and other attributes as appropriate) when the user enters their card ID into a TextField then click a suitably named button. In your program use a HashMap to locate a card given a card ID.

The program user will be able to delete a card. When a card is deleted any purchases made by that user are converted to cash purchases i.e. are changed to have a null card ID. Also use a HashMap here to locate a card given a card ID.

The program user will be able to make purchases. The GUI will support the entry of card ID, and category amounts. A button will be clicked to make the purchase. A receipt ID will be issued. A TextArea will show the receipt information i.e. all the data entered for the purchase plus the receipt ID and a total purchase amount. As for assignment 1, five categories must be supported.

The program user will be able to view a summary that shows (i) the number and value of purchases made with a card and (ii) without a card i.e. cash. Then shows that total amount of purchases for each category.

The list of cards and purchases should be maintained in the Program or Shop class. There should be error checking of any numeric quantities. If there is an error the transaction (register a card or make a purchase) will not proceed. An error message and/or highlighting of the affected entry field should be provided.

The good overall design would use a tabbed panel, with a separate tab for registering a card, for viewing cards, and for making a purchase. However, this is not a strict requirement.

Some design ideas for the GUI will be covered on the whiteboard in either the week 5 or week 6 lecture.

While this assignments provides a GUI to enter cards and make purchases, you will also provide batch test data that: (i) creates a single card and makes a single purchase with it (ii) creates several cards of mixed types and makes several purchases including cash one with them.

Students who wish to submit and advanced assignment must ensure that have already met all the standard requirements. That is, the advanced features will only be marked after ensuring that all standard requirements have been met.


### Advanced Level (CSCIT821 students must complete this level)
The advanced level includes all the requirements of the standard level plus the following features:

1. An additional form (tab) that allows a user to add or delete purchase categories. You must have a category called “Other”. When a category is deleted, all amounts in the existing purchases are moved into this “Other” category. A consequence of this is the form that allows a user to make a purchase must have a dynamic number of TextFields, to support one TextField for each category amount. You may need to manually create the corresponding Java swing code for this.
2. The form that allows a user to view the list of cards will offer three sorts: (i) cards in the order that were created (ii) in the order of their names (iii) in the order of their points. You must make appropriate use of the Java sort methods here i.e. use an appropriate Collects.sort method.


### Marking (CSIT121 students)
#### *Advanced (up to 6 marks)*
Must be marked in the week 8 labs. Your work must be ready for marking **30 minutes** after the start of the laboratory. You work is likely to be audited, that is, you will be asked to modify and/or explain your code. Your code is expected to be well structured and easy to read.

#### *Standard (up to 4 marks)*
Must be marked in the week 8 or 9 lab. Your work must be ready for marking **40 minutes** prior to the end of the laboratory. You work may be audited, that is, you may be asked to modify and/or explain your code. Your code is expected to be well structured and easy to read.

~~### Marking (CSIT821 students)~~
~~#### Advanced in week 8 (up to 6 marks)
Your work must be ready for marking 30 minutes after the start of the laboratory. You work is likely to be audited, that is, you will be asked to modify and/or explain your code. Your code is expected to be well structured and easy to read.~~

~~#### Advanced in week 9 (up to 4 marks)
Your work must be ready for marking 30 minutes after the start of the laboratory. You work is likely to be audited, that is, you will be asked to modify and/or explain your code. Your code is expected to be well structured and easy to read.~~

**You must submit your Java project** (zip your project directory) **via the eLearning site** (the assignment 2 drop box) once you have been successfully marked *in the laboratory.*
