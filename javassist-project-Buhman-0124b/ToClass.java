package ex04.toclassB;

import java.util.Scanner;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ToClass {
   public static void main(String[] args) {
      try {
         // Hello orig = new Hello(); // java.lang.LinkageError
     	 Scanner input = new Scanner(System.in);  
     	 int option = 0;
     	 String methodToModify = "";
     	 boolean flag1 = false;
     	 boolean flag2 = false;
     	 boolean superclassFlag = false;
     	 while(option != 3)
     	 {
     		System.out.println("Enter 1 to modify add.  Enter 2 to modify remove. Enter 3 to quit.");
     	 	option = input.nextInt();
     	 	if(option == 3)
     	 	{
     	 		break;
     	 	}
     	 	else if(option == 1)
     	 	{
     	 		methodToModify = "add";
     	 		if(flag1 == true)
     	 		{
     	 			System.out.println("[WRN] This method 'add' has been modified!!");
     	 			continue;
     	 		}
     	 		flag1 = true;
     	 	}
     	 	else if(option == 2)
     	 	{
     	 		methodToModify = "remove";
     	 		if(flag1 == true)
     	 		{
     	 			System.out.println("[WRN] This method 'remove' has been modified!!");
     	 			continue;
     	 		}
     	 		flag2 = true;
     	 	}
     	 	else
     	 	{
     	 		continue;
     	 	}
     	 	
     	 	System.out.println("Please enter a usage method, an increment method, and a getter method:");
    	 	String usage = input.next();
    	 	String increment = input.next();
    	 	String getter = input.next();
    	 	//scanner 2 options to modify 1 class
    	 	//if user selects first option, modify rectangle
    	 	//show menu again
    	 	//if user selects second option, modify rectangle again
    	 	//have to un-freeze it.

         	ClassPool cp = ClassPool.getDefault();
            CtClass rectangle = cp.get("target.Rectangle");
            rectangle.defrost();
            CtClass point = cp.get("target.Point");
            point.defrost();
            if(superclassFlag == false)
            {
            	rectangle.setSuperclass(point);
            	superclassFlag = true;
            }
         	CtMethod m = rectangle.getDeclaredMethod(methodToModify);
         	CtConstructor declaredConstructor = rectangle.getDeclaredConstructor(new CtClass[0]);
         
         	m.insertAfter("{ " //
        		 + increment + "();\nSystem.out.println(\"value: \" + " + getter + "();");

         	Class<?> c = rectangle.toClass();
            target.Rectangle h = (target.Rectangle) c.newInstance();
            if(option == 1)
            {
            	h.add();
            }
            else
            {
            	h.remove();
            }
         	
     	 }
      } catch (NotFoundException | CannotCompileException | //
            InstantiationException | IllegalAccessException e) {
         System.out.println(e.toString());
      }
   }
}
