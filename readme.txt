FIFO:

Made of use of an ArrayList for the frames. Each iteration will be displayed with an indication of "H" if it is a hit or "F" if a page fault occurs. A variable of which is index to be replaced is updated at each page inserted into the frame.

Reference: https://www.youtube.com/watch?v=ff7IaEUmgAI
View image at: https://drive.google.com/file/d/0ByPlnmGfKtpKcm4zTjRIVDVvbjA/view?usp=sharing

LRU:

Made use of an array to keep track of which index was recently used. If a page fault occurs, the mininum value among the count array will check which index has been least recently used. Each iteration will be displayed with an indication of "H" if it is a hit or "F" if a page fault occurs.

Reference: http://www.mathcs.emory.edu/~cheung/Courses/355/Syllabus/9-virtual-mem/LRU-replace.html
View image at: http://www.mathcs.emory.edu/~cheung/Courses/355/Syllabus/9-virtual-mem/LRU-result.gif


LFU:

Made use of an array to store the frequency count of each index in the frames. The index with least amount of frequency will be replaced when a page fault occurs. Each iteration will be displayed with an indication of "H" if it is a hit or "F" if a page fault occurs.

Reference: http://lfucode.blogspot.com/

Sample: 
Frame size: 3; Reference length = 12
Pages = {2,1,0,1,4,2,2,3,4,2,5,2}
frame : 2 
frame : 2 1 
frame : 2 1 0
frame : 2 1 0
frame : 4 1 0
frame : 2 1 0
frame : 2 1 0
frame : 2 1 3
frame : 2 1 4
frame : 2 1 4
frame : 2 5 4
frame : 2 5 4

For the executable file, go to the /dist folder of this project. Run java -jar 125MP2.jar in your terminal.
