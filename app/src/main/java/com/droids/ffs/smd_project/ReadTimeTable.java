package com.droids.ffs.smd_project;


import android.util.Log;

import com.droids.ffs.smd_project.SQLite.Class;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadTimeTable {


    private static List<Class> classList;



    public static List<Class> read(InputStream is) {

//            FileInputStream is;
            try {

                classList = new ArrayList<>();

//                Log.v("test",filePath);
//                InputStream myInput;
                // initialize asset manager
//                AssetManager assetManager = getAssets();
                //  open excel file
//                myInput = assetManager.open("timetable.xlsx");

//                is = new FileInputStream(new File("content://com.android.providers.downloads.documents/document/raw%3A%2Fstorage%2Femulated%2F0%2FDownload%2FTime%2BTabe%2BV-Final.xlsx"));

                XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
                // Get the first sheet from workbook
                XSSFSheet mySheet = myWorkBook.getSheetAt(0);
                // We now need something to iterate through the cells.
                Iterator<Row> rowIter = mySheet.rowIterator();

                Log.v("abc",myWorkBook.toString());

                String text = "";

                int rowNumber = 1;
                while (rowIter.hasNext()) {
                    XSSFRow myRow = (XSSFRow) rowIter.next();

//				Starting and ending row limits
                    if (rowNumber > 5 && rowNumber < 125) {

                        Iterator<Cell> cellIter = myRow.cellIterator();

                        int columnNumber = 1;
//					Column limit
                        while (cellIter.hasNext() && columnNumber < 13) {

                            XSSFCell myCell = (XSSFCell) cellIter.next();

//						Add # after each cell, even if its empty
                            text += myCell.toString() + "#";
                            columnNumber++;
                        }

//					Add @ after each row even if its empty
                        text += "@";
                    }

                    rowNumber++;

                }

                String[] rows = text.split("@");

//		    System.out.println(rows.length);

                rowNumber = 1;

                Class[] classes = new Class[20];
                for (int i = 0; i < classes.length; i++) {
                    classes[i] = new Class();
                }

                for (String r : rows) {
                    int columnNumber = 0;

                    if (rowNumber == 1 || rowNumber == 17 || rowNumber == 25 || rowNumber == 41 || rowNumber == 50 || rowNumber == 66 ||  rowNumber == 74 || rowNumber == 90 || rowNumber == 97 || rowNumber == 113) {
                        String[] timeSlots = r.split("#");

                        int j = 0;
                        for (String c : timeSlots) {

                            if (c.matches("Day") == false && c.matches("Venue") == false && c.matches("") == false && c.matches("Labs") == false) {

                                classes[j] = new Class();
//		    					System.out.println(c.split("-")[0]);
                                classes[j].setClassStartTime(c.split("-")[0]);

                                classes[j].setClassEndTime(c.split("-")[1]);
                                j++;
                            }

                        }

                    }

                    else {
                        String[] columns = r.split("#");

                        int j = 0;
                        for (String c : columns) {

//	    					System.out.println("-"+c);
                            if (j == 0) {
                                for (Class c1 : classes) {
//		    						System.out.println("-"+c);
                                    if (rowNumber < 25) {
                                        c1.setClassDay("Monday");
                                    }else if (rowNumber >= 25 && rowNumber < 50) {
                                        c1.setClassDay("Tuesday");
                                    }else if (rowNumber >= 50 && rowNumber < 74) {
                                        c1.setClassDay("Wednesday");
                                    }else if (rowNumber >= 74 && rowNumber < 97) {
                                        c1.setClassDay("Thursday");
                                    }else if (rowNumber >= 97) {
                                        c1.setClassDay("Friday");
                                    }

                                }

                            } else if (j == 1) {
                                for (Class c1 : classes) {
//		    						System.out.println("+"+c);
                                    if (c != null) {
                                        c1.setClassRoom(c);
                                    }

                                }

                            }

                            if (j >= 2) {
                                if (c.contains("Break") == false) {
//	    							System.out.println("#"+c);

                                    if (c.compareTo("") != 0) {
                                        classes[columnNumber].setCourseName(c);
                                        columnNumber++;
                                    } else {
                                        classes[columnNumber].setCourseName(null);
                                        columnNumber++;
                                    }

                                }

                            }
                            j++;

                        }

                    }

//		    		System.out.println(rowNumber);
//			    	System.out.println(r);

                    if (rowNumber >= 2) {
                        for (int i = 0; i < columnNumber; i++) {



//                            Log.v("Test", String.valueOf(i));

                            if (classes[i] != null){
                                if (classes[i].getCourseName() != null){
                                    classList.add(classes[i]);
                                    Log.v("Test",
                                            classes[i].getCourseName() + "##" + classes[i].getClassStartTime()+"-"+classes[i].getClassEndTime() + "##" + classes[i].getClassDay() + "##" + classes[i].getClassRoom());

                                }

                            }


                        }

                        System.out.println("\n\n");
//		    		if (rowNumber == 9) {
//		    			break;
//		    		}
                    }

                    rowNumber++;

                }

//		    System.out.println(text);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return classList;

        }




}
