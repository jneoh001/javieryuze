Each cinema file will be titled: 'cinema_XXX_YYY'
XXX is the cineplex ID and YYY is the cinema hall number appended with 0s at the front
An example is 'cinema_DTE_004' for hall 4 of Downtown East's cineplex.

In each cinema file, please follow the format below to create it in text.

==========
Format
==========
Hall number
Cinema type (PLATINUM/STANDARD) // Note: In CAPS
Total Seats No
Occupied Seats No
Seat Layout (ASCII symbols, using legends below)

==========
Legend:
==========
0 = Empty seat, to be displayed visually as ' '
1 = Occupied seat, to be displayed visually as 'x'

The rest are to be displayed as-is

==========
Example:
==========
cinema_DTE_004 <---- FILENAME

3
PLATINUM
30
0
|               SCREEN              |
|___________________________________|

      1  2  3  4  5  6     7  8      
A  -  - |0||0||0||0||0| - |0| -  -  A
B  - |0||0||0||0||0||0| - |0||0| -  B
C  - |0||0||0||0||0||0| - |0||0| -  C
D  - |0||0||0||0||0||0| - |0||0| -  D
E  - |0||0||0||0||0||0| - |0||0| -  E
F  - |0||0||0||0||0||0| - |0||0| -  F
G  - |0||0||0||0||0||0| - |0||0| -  G
   -  -  -  -  -  -  -  -  -  -  -  
            ____________
           |            |
           |  ENTRANCE  |    