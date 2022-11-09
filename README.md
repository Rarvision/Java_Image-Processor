# Image Processor Overview
* This image processor has both console-based view and GUI followed the architecture pattern of MVC.
* This image processor can handle various type of processing method including bluring, sharpening, dithering, sepia , gray-scale, histogram equalization and mosaic effect with adjustable amount of seeds.
* Different kinds of diagram generators are also incorporated.
* Integrated test classes are incorporated in the source codes.

## Features
This image processor has following functions:
- Bluring an image
- Sharpening an image
- Converting an image to a gray scale image
- Converting an image to a enhanced gray scale image using histogram equalization
- Concerting an image to a gray scale image with effect of edge detection
- Converting an image to a sepia-toned image
- Converting an image to a dithering image
- Applying mosaic effect to an image with adjustable amount of seeds

Beyond above, this image processor can let user generate simple pictures with given arguments:
- Generate a rainbow pattern
- Generate a checker board pattern
- Generate a national flag

The processor can automatically done multiple operations with only one boot. Users should provide proper configuration file (script) to input the order of operations.

## Limitation
- The implementation of "Mosaic" can be further optimized. Now the runtime can be very long if large amount of seeds (> 20000) are provided.

## How to use
This procrssor provide two different user interfacrs. You can choose a console-based view or GUI view. To use the console-based view, run src/script/TextDriver.java. To use the GUI-based view run src/script/GuiDriver.java.

To use the console-based image processor, user should open the IDE and change the Run Configuration, adding the file path of the script with extension into the program arguments.**Make sure that the file name should be the last argument.** If the path added is not an absolute path, it will be considered as a relative path to root directory of the project. Finally, run the java application through the *src\script\TextDriver* class then processing will launch according to the orders in the provided script file. User can check the processing status through the console.

To use the GUI-based image processor, user don't need to configure an script file. However, user can still lauch a script using GUI. In this case, the Run Configuration isn't need to be manully configured. A GUI-based processor is run through *src\script\GuiDriver.java*.

To use the graph-generating functions, just click the blue "Draw a Graph" button on the up left corner of the GUI window. Subsequent operations are straightforward.

## How to write a script
To successfully run the program, a correct script is needed. The script has following basic words:
- load
- save
- blur
- sharpen
- sepia
- grayscale
- edge-detection
- histogram-equalization
- dithering

Words above are all case sensative. **Please type words in lowercase in order to successfully run the processor.**

The word *blur*, *sharpen*, *sepia*, *grayscale*, *edge-detection* and *histogram-equalization* constitute a legal operation by them self. For example:
```
sharpen
```
It means sharpen a loaded image using the algorithm of this image processor.

The word *load*, *save* need one additional text argument representing file name to form a legal operation. For example:
```
load realImage.png
```
It means that the processor should load image located in the root directory with the file name of "realImage.png". 
```
save D:\Desktop\street.png
```
It means that processor should save the image with the name "street.png" with the location of D:\Desktop. 

For both load and save, if the second argument is not provided as an absolute path, it will be considered as a relative path of the root directory of the project. Invalid file path will cause exception. To avoid exception, the extension of input or output files' name must be one of the following: .jpg, .png, .jpeg and .gif. The case of extension doesn't matter.

**Note: the path of the file MUSTN'T contain any blank space.** It will cause error.

The script is executed line by line. Make sure that there should be only one operation in each line. The cursor will jump to the next line if an operation is done so the redundant operation in a line will never be excuted. **NOTE: there shouldn't be ANY blank line in your script.** The program will terminated meeting a blank line and show a message of error.

## Example of using a text-based processor
Suppose there are 2 different images we want to process, and they are already in the root directory of the processor. The first one named "food.png", we want to blur it and apply sepia-tone effect on it then saved it as "food-blur-sepia.png". The second one named "street.png". Firstly we want to sharpen it and saved the result as "street-sharpen.png" located in D:\Desktop. Secondly we want to apply mosaic effect to the original picture with 2000 mosaic seeds and saved the result as street-mosaic2000.png in the root directory of the processor.
To achieve this result, we should write a script like this:
```
load food.png
blur
sepia
save food-blur-sepia.png
load street.png
sharpen
save street-sharpen.png
load D:\Desktop\street.png
mosaic 2000
save steet-mosaic2000.png
```
Make sure the script is saved in root directory. Added the aboslute or relative path of the script (*e.g.* script1.txt) at the last of the command-line arguments. Run *src\script\TextDriver.java* as java application, the operation will done automatically.

## Introduction of using a GUI-based processor
After launching the GUI-based processor *src\script\GuiDriver*, user can load, save and apply different effects by clicking buttons or press keys. The user interface is very straight forward.

All example images used in this project are photographed by Mao Zhang. These pictures are authorized to be used in and only in the class of CS 5004 in Northeastern University. A message bar is provided at the bottom of the window to show status of loading, saving or error occuring. User can still lauch a script by select File > Processing with Script File. In this case, the processed images will not be displayed but the status of processing through the message bar.

For some reasons, the pictures saved by GUI-based processor may only be accessed after the shutting down of the processor.

User can also use keyboard shortcut to control the processor:
Alt + b: blur the image
Alt + p: sharpen the image
Alt + s: apply a sepia effect
Alt + g: apply a grayscale effect
Alt + e: apply a edge-detection grayscale effect
Alt + h: apply an enhanced grayscale effect using histogram equalization
Alt + d: apply a dithering effect
Alt + m: apply a mosaic effect
Alt + r: reset the processing image to origin
Ctrl + l: open the window of loading
Ctrl + s: save the image (will cover the original one)
Ctrl + a: open the window of saving 
Ctrl + t: open the window of selecting script file
Alt + w: open the graph generating dialog 

# Examples
There are two demo images in the /res folder. One is street.png, which is used to apply all processing methods of this processor. The other is food.png, which is used to test combinations of these processing methods.

## Citation
All images in the /res folder are photographed by Mao Zhang. They are authorized to be used in and only in the CS 5004 class of Northeastern University.

## Statement
**© 2022 Mao Zhang, Guided by Prof. Maria Jump, Northeastern University**

