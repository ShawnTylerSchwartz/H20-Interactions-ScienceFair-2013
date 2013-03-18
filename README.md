#H20 Interactions Science Fair 2013

##Computational Simulation of Multidimensional H<sub>2</sub>O Molecule Interactions

***Authored by Shawn Tyler Schwartz, High Tech Los Angeles Honors Chemistry 10B
<br />Advised by Wun Chiou, Chemistry  & Daniel Perahya, Physics***


###Abstract
This experiment intends to answer the question, “Can the most optimized positioning of water molecules in a multidimensional system, in order to reach the lowest possible system energy, be created by randomly generated simulations?” The hypothesis that the most optimized system can be generated via simulation after initial randomized placement of water molecules will be accepted if the data returned from the program is comparable and realistic data, in comparison to a real system of interacting water molecules. The hypothesis will be rejected if the program is mathematically incapable of producing a system close to minimal system energy. Minimal system energy will be determined by the angle of the individual water molecules and their distances apart from one another. The total energy of the system will then be calculated by taking the summation of the individual iterations of Columb Energy between each atom of each water molecule in the system. Columb Energy will be calculated through looping processes and dipole moment interaction algorithms. 

###Materials
Little physical materials were used in this software based experiment.
> a.  Apple MacBook Pro, Mid 2012
> > 	Retina 
> > 	Processor 2.3 GHz Intel Core i7
> > 	Memory 8GB 1600 MHz DDR3
> > 	Graphics NVIDIA GeForce GT 650M 1024 MB
> >   Software OS X 10.8.3 (12D78)
>
> b.	Netbeans IDE
> > 	Version 7.2.1
> > 	Java 1.7.0_10; Java HotSpot(TM) 64-Bit Server VM 23.6-b04
>
> c.  Jmol
> > 	http://jmol.sourceforge.net/
> > 	Version 13.0.12
>
> d.	Git
> > 	Version 1.7.10.2 (Apple Git-33)
>
> e.	Github
> > 	https://github.com/sublimesynthesis/H20-Interactions-ScienceFair-2013/
>
> f.	Microsot Excel 2011

###Procedure
To come to a conclusion in regards to my hypothesis, a program needed to written. Using Java and the Netbeans IDE, a dynamic program was written to generate two random water molecules in a three-dimensional plane. The water molecules are first plotted at the origin. Using known geometric properties of the water molecules, the Hydrogen atoms were plotted properly in regards to bond length and angle. The Oxygen molecule is plotted at (x,y,z)→(0,0,0), the left Hydrogen molecule at (-24,0,93), and the right Hydrogen molecule at (96,0,0). These values are taken from the tangent of the bond length and bend angle from the Oxygen molecule plotted at the origin. After the initial plot at the origin, each molecule is randomly rotated to a new geometry. The following mathematical methods are used for the rotation:

![Rotation Matrix From Wikipedia](http://upload.wikimedia.org/math/5/1/4/5148f88bf9e6811e35615c08d2839793.png)

The random angle is generated on a scale from 0.1 to π radians. Finally, the coordinates of the newly rotated water molecules are each added to a translation constant. This causes the molecule to translate to its final position in the three-dimensional coordinate plane. These new final values are stored into variables in the software, and are used during the calculation process. In the calculation process, the positions are passed into a general method for simple point charge Columb interaction calculations. The method accepts the arguments as follows: (moleculeOneX, moleculeOneY, moleculeOneZ, moleculeTwoX, moleculeOneY, moleculeOneZ). The distance is then found using the distance formula:

![3D Distance Formula From Wikipedia](http://upload.wikimedia.org/math/7/1/2/7122dc6c69436cf2ec0814ec2e397e02.png)

Using this calculated distance, the Columb force calculation is then used. The value from the equation is stored into a variable and returned by the method. All of the nine iterations are each stored into their own variable, and the sum of them is calculated to display the final system Columb energy.

E=(kδ_1 δ_2)/d
E = Energy
K = Columb constant (value stored in program)
Delta One (1) = Point Charge of Hydrogen (value stored in program)
Delta Two (2) = Point Charge of Oxygen (value stored in program) 
D = Distance (from distance formula above)

∑_(n=0)^8▒〖iteration〗_n =

Final Columb System Energy

After generating fifteen different system situations, each geometry was plotted along with the system energy. The geometry of the water molecules is the independent variable. The final system energy is the dependent variable. Jmol molecular rendering software was used to draw each model of each generated water molecule geometry, considering angle and position of the molecules in the system.

The trends can be seen on the results page.
![Alt text](/path/to/img.jpg)
