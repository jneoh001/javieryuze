<<FILE: base_price.txt>>
REFERENCE FILE
Sets the base price of every single ticket, from which the ticket price is to be modified from
        Format: BASE | 10       (i.e. base price = $10)

------------------------------------------------------------------------------------------------

<<FILE: holiday_list.txt>>
REFERENCE FILE
        Format: YYYY-MM-DD | HOLIDAY NAME     (e.g. 2018-12-31 | CHRISTMAS DAY )

Stores the map of all the available holidays. 
This file is used to check if a particular movie showtime falls on a holiday.
If it falls on a holiday, then apply the holiday price modifier to the base price

------------------------------------------------------------------------------------------------

<<FILE: ticket_type.txt & day_of_week.txt & movie_format.txt & holiday.txt & base_price.txt & cinema_type>>
REFERENCE FILE 
        Format: MODIFIER | AMOUNT        (e.g. SUNDAY | 3) (e.g. STUDENT | -3)

These will hold the price modifier amount for every possible scenario. HOWEVER these files will not be used directly to do price modifications
These files are broken up so that the staff can more easily distinguish the different price modifier categories
The systemSettingsManager will perform CRUD operations on these files. 
Once that is done, perform the EXACT SAME OPERATION on the price_modifier.txt file

Modifiers available:    ticket_type    -->  STANDARD, STUDENT, SENIOR
                        day_of_week    -->  MON, TUE, WED, THU, FRI, SAT, SUN
                        movie_format   -->  TWOD, THREED, IMAX
                        holiday        -->  HOLIDAY
                        base_price     -->  BASE
                        cinema_type    -->  PLATINUM, NORMAL

------------------------------------------------------------------------------------------------

<<FILE: price_reference.txt
PRICE MODIFER FILE
        Format: MODIFIER | AMOUNT

This will be interpreted by the manager in charge of pricing to apply the corresponding (appropriate) price modifications
Some price modification rules
    1. Set BASE first
    2. Apply all modifications
    2. If HOLIDAY modification is applied, do not apply FRIDAY, SATURDAY, SUNDAY modifications (vice versa)