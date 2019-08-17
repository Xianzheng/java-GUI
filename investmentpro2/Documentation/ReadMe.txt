Student Name: Xianzheng Fang
Student Number: 0847616

project discription: 

This Project is based on Assignment One. It uses single ArrayList to store two investment. The parent class is Investment,
and stock class and mutualfund class are two subclass which inheritance investment class. All the writing in the platform
will come it to data.txt automatically. When quit program, user needs to load the file. and the data will come to the program
again. It use hashmap to check input validity and research.  

Instructions on how to build and compile:
The attributer are symbol, name, price and quantity.
symbol and name should be unique, if not already exist
quantity only can be integer. No negative number and decimals.
price can not be negative, but support decimals.
All the input data will write to the data file automatically
And type load will come to the program.

Limitation and assumpation:
When program check if symbol or name is Unique, It use hashmap.contain(“Key”)to check
both symbol and name are both key,Both of them only allowed enter once;
When count getGain, it should count right, but if load file new, the date will be refresh.

Possible investment:
The most importane bug for the program is the key will be replace by the same key.
The outcome will be replaced by the last one.
In search, like there are two key Toronto bank, bank of Montreal, search bank only show the information about
last one.I try to solve by HashMap and ArrayList together but failed



