import java.util.*;
import java.io.*;
import java.text.*;


public class MyContactBook {
	public static void main(String[] args){
		Scanner sc=null;
		Scanner stringReader=null;
		try{
			int ch=0;
			sc=new Scanner(System.in);
			boolean validFile=false;
			stringReader=new Scanner(System.in);
			System.out.println("\n\t\t\tCONTACTS APP");
			System.out.println("--------------------------------------------------------------------------------");
			while(ch != 5){
				System.out.println("\nTO CREATE PHONEBOOK->PRESS 1");
				System.out.println("TO LOAD A CREATED PHONEBOOK->PRESS 2");
				System.out.println("TO DELETE CREATED PHONEBOOK->PRESS 3");
				System.out.println("TO LIST ALL PHONEBOOKS->PRESS 4");
				System.out.println("TO EXIT->PRESS 5");
				while(!sc.hasNextInt())
				{
					System.out.println("\nONLY NUMBERS RANGED FROM 1 - 5 SHOULD BE ENTERED..RETRY");
					sc.nextLine();
				}
				ch = sc.nextInt();
				switch(ch){
					case 1: 
						System.out.println("\nENTER NAME OF PHONEBOOK(Ex MyPhoneBook).DONT GIVE ANY EXTENSION.");
						validFile = false;
						String contactBookName = "";
						while(!validFile){
							contactBookName = stringReader.nextLine();
							if(contactBookName.equals("")){
								System.out.println("\nPHONEBOOK NAME MUST NOT BE EMPTY");
								System.out.println("\nENTER NAME OF PHONEBOOK(Ex MyPhoneBook).DONT GIVE ANY EXTENSION.");
								continue;
							}
							if(!(contactBookName.contains("/")||contactBookName.contains("\\")||contactBookName.contains(":")||
									contactBookName.contains("*")||contactBookName.contains("?")||contactBookName.contains("/")||
									contactBookName.contains("\"")||contactBookName.contains("<")||contactBookName.contains(">")||
									contactBookName.contains("|"))){
								String extension[] = contactBookName.split(".");
								contactBookName = contactBookName + ".txt";
								File contactBook = new File(contactBookName);
								if(!contactBook.exists()){
									System.out.println("\nNEW PHONEBOOK CREATED::"+contactBookName);								
									validFile = true;
								}
								else{
									System.out.println("\nTHIS PHONEBOOK NAMED "+contactBookName+" ALREADY EXISTS\nPLEASE ENTER A DIFFERENT NAME" );
								}	
							}		
							else{
								System.out.println("\nFILE NAME CANNOT CONTAIN : /,\\,\",*,?,<,>,|");
								System.out.println("\nENTER NAME OF CONTACTS BOOK TO CREATE WITHOUT FILE EXTENSION ");
							}
						}
						contactBookOperations(contactBookName);
						break;
					case 2: 
						contactBookName = "";
						System.out.println("\nENTER NAME OF PHONEBOOK(Ex MyPhoneBook) TO LOAD.DONT GIVE ANY EXTENSION.");
						contactBookName = stringReader.nextLine();
						while(true){
							if(contactBookName.equals("")){
								System.out.println("\nPHONEBOOK NAME MUST NOT BE EMPTY ");
							
							}else{
								break;
							}
						}
						contactBookName += ".txt";
						File contactBook = new File(contactBookName);
						if(!contactBook.exists()){
							System.out.println("\nA PHONEBOOK WITH NAME "+contactBookName+" DOES NOT EXIST");
						}else{
							contactBookOperations(contactBookName);
						}
						break;
					case 3: 
						contactBookName = "";
						System.out.println("\nENTER NAME OF PHONEBOOK WITHOUT EXTENSION TO DELETE");
						contactBookName = stringReader.nextLine();
						if(contactBookName.equals("")){
							System.out.println("\nPHONEBOOK NAME CANNOT BE EMPTY ");
						}
						contactBookName += ".txt";
						contactBook = new File(contactBookName);
						if(!contactBook.exists()){
							System.out.println("\nA PHONEBOOK WITH NAME "+contactBookName+" DOES NOT EXIST");
						}else{
							contactBook.delete();
							System.out.println("\nA PHONEBOOK "+contactBookName+" DELETED");
						}
						break;
					case 4:
						String curDirectory=System.getProperty("user.dir");
						listPhoneBooks(curDirectory);
						break;
					case 5: 
						System.out.println("\nSUCCESSFULLY EXITED");
						break;
					
					default: 
						System.out.println("\nENTER ONLY NUMBERS RANGED FROM 1 - 5");
					 	break;
					
				}
			}
		}catch(Exception e){
			System.out.println("AN ERROR OCCURED!!");
			e.printStackTrace();
		}finally{
			if(sc!=null)	sc.close();
			if(stringReader!=null) stringReader.close();
		}
	}

	public static void contactBookOperations(String contactBookName)throws IOException, ParseException{
		FileWriter fr = new FileWriter(contactBookName,true);
		BufferedReader fileReader = new BufferedReader(new FileReader(contactBookName));
		BufferedWriter fileWriter = new BufferedWriter(fr);
		String address = "";
		String name = "";
		boolean validName = false;
		String dateOfBirth = "";
		boolean dateValid = false;
	 	String petName = "";
	 	int tag = 0;
	 	String contactType = "";
	 	boolean invalidTag = false;
	 	boolean moreEmail = false;
	 	String email = "";
	 	String emailChoice = "";
	 	boolean morePhone = true;
	 	String phoneChoice = "";
	 	String phoneList = "";
	 	String contactAdded="";
	 	long phoneNum = 0;
		int choice = 0;
		ArrayList<String> nameList = new ArrayList<String>(); // arrayList to hold names of contacts in the file...
		Scanner sc = new Scanner(System.in);
		String searchString = "";
		Scanner stringReader = new Scanner(System.in);
		String line = "";
		String totalDetails = "";
		//System.out.println(fileReader);
		while(choice!=6){
			System.out.println("\nTO ADD CONTACT->PRESS 1 ");
			System.out.println("TO EDIT CONTACT->PRESS 2 ");
	 		System.out.println("TO REMOVE CONTACT->PRESS 3 ");
			System.out.println("TO LIST CONTACTS->PRESS 4 ");
			System.out.println("TO SEARCH CONTACT->PRESS 5 ");
			System.out.println("TO GO BACK TO PREVIOUS MENU->PRESS 6 ");					
			while(!sc.hasNextInt()){
				System.out.println("\nENTER ONLY NUMBERS RANGED FROM 0 - 6");
				sc.next();
			}
							
			choice = sc.nextInt();
			switch(choice){
				//ADD A NEW CONTACT........
				case 1:// add contact option.............
					// add name
					dateValid = false;
					invalidTag = true;
					moreEmail = true;
					morePhone = true;
					email = "";
					phoneList = "";
					nameList.clear();
					System.out.println("\nENTER NAME OF THE PERSON(Ex: Aditya Dey)");
					validName = false;
					fileReader = new BufferedReader(new FileReader(contactBookName));
					while(!validName){
						name = stringReader.nextLine();			// read contact name from user
						name = name.trim();
						if(name.equals("")){
							System.out.println("\nNAME OF THE PERSON CANNOT BE BLANK\nENTER NAME OF THE PERSON(Ex: Aditya Dey)");
							continue;
						}
						while( ((line=fileReader.readLine())!=null))	 // read till end of file
						{
							String s = line.substring(0,line.indexOf('='));	// add each name that exists in the file
							if(!nameList.contains(s)){
								nameList.add(s);
							}
						}
						//System.out.println(nameList);			
						if(nameList.contains(name)) 	//if name already exists, print error message
						{
							System.out.println("\nA CONTACT NAMED "+name+ " ALREADY EXISTS.");
							System.out.println("\nPLEASE ADD ANOTHER NAME(Ex: Aditya Dey)");
					
						}
						else{
							validName = true;
							nameList.add(name);
						}
					}
					//date of birth
					System.out.println("\nENTER DOB OF THE PERSON IN DD/MM/YYYY FORMAT Ex: 12/05/1992 OR PRESS ENTER TO SKIP");
					while(!dateValid){
						dateOfBirth = stringReader.nextLine();
						if(dateOfBirth.trim().compareTo("")==0) dateValid=true;
						else//doubt? next() skips next scan...			
							dateValid = isValidDate(dateOfBirth);
						if(!dateValid){
							System.out.println("\nINVALID DATE...\nENTER DOB OF THE PERSON IN DD/MM/YYYY FORMAT Ex: 12/05/1992");
						}
					}
					
				
					//	System.out.println(dateOfBirth);
					//add contact type
					System.out.println("\nENTER PET NAME OF THE PERSON OR ENTER TO SKIP");
					petName = stringReader.nextLine();
				
					System.out.println("\nCHOOSE CONTACT TYPE...");
					System.out.println("\nFOR FAMILY->PRESS 1 ");
					System.out.println("FOR FRIENDS->PRESS 2 ");
					System.out.println("FOR ASSOCIATES->PRESS 3 ");
					System.out.println("FOR OTHERS->PRESS 4 ");
				
					while(!sc.hasNextInt()){
						System.out.println("\nENTER ONLY NUMBERS RANGED FROM 1 - 4");
						sc.nextLine();
					}
					tag = sc.nextInt();
				
					while(invalidTag){	
						switch(tag){
							case 1: 
								contactType = "Family";
								invalidTag = false;
								break;
							case 2:
								contactType = "Friend";
								invalidTag = false;
								break;
							case 3:
								contactType = "Associate";
								invalidTag = false;
								break;
							case 4:
								contactType = "Others";
								invalidTag = false;
								break;
							default:
								System.out.println("\nENTER ONLY NUMBERS RANGED FROM 1-4");
								invalidTag = true;
								break;
						}
					}
					//System.out.println(contactType);
					System.out.println("\nENTER ADDRESS IN ONE LINE OR PRESS ENTER TO SKIP");
					address = stringReader.nextLine();
					//System.out.println(address);
					while(moreEmail){
						System.out.println("\nENTER EMAIL ADDRESS OR PRESS ENTER TO SKIP");
						email = email+","+stringReader.nextLine();
						//System.out.println(email);
						if(!(email.trim()).equals(",")){
							System.out.println("\nTO ADD EMAIL ADRESS->PRESS 'Y' OR 'y'..TO STOP ADDING EMAIL PRESS ANY OTHER KEY");
							emailChoice = stringReader.nextLine();
							if(emailChoice.equals("y")||emailChoice.equals("Y")){
								moreEmail = true;
							}
							else{
								moreEmail = false;
							}
						}else{
							moreEmail = false;
						}
					}
					email = email.substring(1,email.length());
					//System.out.println(email);
						
				
					while(morePhone){
						System.out.println("\nENTER CONTACT NUMBER");
					
						while(!sc.hasNextLong()){
							System.out.println("\nINVALID CONTACT NUMBER.\nENTER A VALID CONTACT NUMBER");
							sc.next();
						}
						phoneNum = sc.nextLong();
						phoneList = phoneList+","+phoneNum;
						sc.nextLine();		//doubt? not included: doesnt read phonechoice.....
					
						System.out.println("\nTO ADD MORE PHONE NUMBERS->PRESS 'Y' OR 'y' \nOTHERWISE PRESS ANY OTHER KEY");
						phoneChoice = sc.nextLine();
					
						if(phoneChoice.equals("y")||phoneChoice.equals("Y")){
							morePhone = true;
						}
						else{
							morePhone = false;
						}
					}
					phoneList = phoneList.substring(1,phoneList.length());
					System.out.println("FOLLOWING DETAILS HAS BEEN SAVED IN THE PHONEBOOK");
					System.out.println("---------------------------------------------------");
					System.out.println("["+name+","+petName+","+contactType+","+address+","+dateOfBirth+","+email+","+phoneList+"]");
					totalDetails = name+"="+dateOfBirth+":"+petName+":"+contactType+":"+address+":"+email+":"+phoneList+":"+new Date();
					fileWriter.write(totalDetails);
					fileWriter.newLine();
					fileWriter.flush();
					break;
				//EDITING A CONTACT
				case 2: 
					String editName = "";
					String details = "";
					String line3 = "";
					String[] detailParts;
					String newDob = "";
					String newPetName = "";
					String newAddress = "";
					String newContactType = "";
					String newEmail = "";
					String newPhone = "";
					String newString = "";
					String name3 = "";
					choice = 0;
					nameList.clear();
					String[] parts ;
					fileReader = new BufferedReader(new FileReader(contactBookName));
					while( ((line=fileReader.readLine())!=null))	 // read till end of file
					{
						String s = line.substring(0,line.indexOf('='));	// add each name that exists in the file
						if(!nameList.contains(s)){
							nameList.add(s);
						}
					}
					BufferedReader editReader = new BufferedReader(new FileReader(contactBookName));
					dateValid = false;
					invalidTag = true;
					String editContact = "";
					System.out.println("\nENTER THE CONTACT TO BE EDITED");
					editName = stringReader.nextLine();
					if(!nameList.contains(editName)){
						System.out.println("\nA CONTACT NAMED "+editName+" DOES NOT EXIST");
					}else{
						while((line3=editReader.readLine())!=null){
							//System.out.println(line3);
							name3 = line3.substring(0,line3.indexOf("="));
							if(!name3.equals(editName)){
								newString = newString+line3+System.getProperty("line.separator");
							}
							else{
								editContact = line3;
							}
						} 
						details = editContact.substring(editContact.indexOf("=")+1,editContact.length());
						detailParts = details.split(":");
						newDob = detailParts[0];
						newPetName = detailParts[1];
						newContactType = detailParts[2];
						newAddress = detailParts[3];
						newEmail = detailParts[4];
						newPhone = detailParts[5];	
						while(choice!=7){
							System.out.println("\nTO EDIT DATE OF BIRTH->PRESS 1");
							System.out.println("TO EDIT PET NAME->PRESS 2 ");
							System.out.println("TO EDIT CONTACT TYPE->PRESS 3 ");
							System.out.println("TO EDIT ADDRESS->PRESS 4 ");
							System.out.println("TO ADD EMAIL->PRESS 5 ");
							System.out.println("TO ADD PHONE NUMBER->PRESS 6 ");
							System.out.println("TO GO BACK TO THE PREVIOUS MENU->PRESS 7 ");
							System.out.print("ENTER YOUR CHOICE::\t");
							while(!sc.hasNextInt()){
								System.out.println("ENTER ONLY INTEGERS IN THE RANGE 1-7");
								sc.nextLine();
							}
							
							choice = sc.nextInt();							
							switch(choice){
								case 1:
									System.out.println("\nENTER DOB OF THE PERSON IN DD/MM/YYYY FORMAT EG: 12/05/1992");
									while(!dateValid){
										newDob = stringReader.nextLine();//doubt? next() skips next scan...			
										dateValid = isValidDate(newDob);
										if(!dateValid){
											System.out.println("\nINVALID DATE...ENTER DOB OF THE PERSON IN DD/MM/YYYY FORMAT EG: 12/05/1992");
										}
									}
									break;	
								case 2:
									System.out.println("\nENTER NEW PET NAME OF THE PERSON OR ENTER TO SKIP");
									newPetName = stringReader.nextLine();
									break;
							
								case 3:	
									System.out.println("\nCHOOSE CONTACT TYPE...");
									System.out.println("\nFOR FAMILY->PRESS 1 ");
									System.out.println("FOR FRIENDS->PRESS 2 ");
									System.out.println("FOR ASSOCIATES->PRESS 3 ");
									System.out.println("FOR OTHERS->PRESS 4 ");
									while(!sc.hasNextInt()){
										System.out.println("\nENTER ONLY NUMBERS IN THE RANGE 1 - 3");
										sc.nextLine();
									}
									tag = sc.nextInt();
									while(invalidTag){
										switch(tag){
											case 1: 
												newContactType = "Family";
												invalidTag = false;
												break;
												
											case 2:
												newContactType = "Friend";
												invalidTag = false;
												break;
											
											case 3:
												newContactType = "Associate";
												invalidTag = false;
												break;
											
											case 4:
												newContactType = "Others";
												invalidTag = false;
												break;
											
											default:
												System.out.println("\nENTER ONLY NUMBERS RANGED FROM 1-4");
												invalidTag = true;
												break;
										}
									}	
									break;					
								case 4:
									System.out.println("\nENTER NEW ADDRESS IN ONE LINE");
									newAddress = stringReader.nextLine();
									break;
									
								case 5:
									moreEmail = true;
									while(moreEmail){
										System.out.println("\nENTER EMAIL ADDRESS OR PRESS ENTER TO SKIP");
										newEmail = newEmail+","+stringReader.nextLine();
										//System.out.println(email);
										if(!(newEmail.trim()).equals(",")){
											System.out.println("TO ADD EMAIL->PRESS 'Y' OR 'y'..TO STOP ADDING EMAIL PRESS ANY OTHER KEY");
											emailChoice = stringReader.nextLine();
											if(emailChoice.equals("y")||emailChoice.equals("Y")){
												moreEmail = true;
											}
											else{
												moreEmail = false;
											}	
										}
										else{
											moreEmail = false;
										}
									}
									break;
									
								case 6:
									morePhone = true;
									while(morePhone){
										System.out.println("\nENTER PHONE NUMBER");
										while(!sc.hasNextLong()){
											System.out.println("\nONLY VALID PHONE NUMBERS ALLOWED");
											sc.next();
										}
										phoneNum = sc.nextLong();
										newPhone = phoneNum+",";
										sc.nextLine();		//doubt? not included: doesnt read phonechoice.....
										System.out.println("\nTO ADD MORE PHONE NUMBERS->PRESS 'y' or 'Y'\nOTHERWISE PRESS ANY OTHER KEY");
										phoneChoice = sc.nextLine();
										if(phoneChoice.equals("y")||phoneChoice.equals("Y")){
											morePhone = true;
										}
										else{		
											morePhone = false;
										}
									}
									phoneList=newPhone;
									phoneList = phoneList.substring(1,phoneList.length());
									break;
									
								case 7: 
									System.out.println("["+editName+","+newPetName+","+newContactType+","+newAddress+","+newDob+","+newEmail+","+newPhone+"]");
									String newDetails = editName+"="+newDob+":"+newPetName+":"+newContactType+":"+newAddress+":"+newEmail+":"+newPhone+":"+new Date();
									newString = newString+newDetails+System.getProperty("line.separator");
									BufferedWriter editWriter = new BufferedWriter(new FileWriter(contactBookName));
									editWriter.write(newString);
									editWriter.close();
									break;
									
								default:
									System.out.println("\nENTER ONLY NUMBERS RANGED FROM 1-7");
									break;
										
							}			
						}
					}
					//dob:petname:tag:address:email1,email2,email3..:ph1,ph2,...:crtdate
				break;
						
				case 3: 	// REMOVING A CONTACT
					BufferedReader removalReader = new BufferedReader(new FileReader(contactBookName));
					String name2 = "";
					String line2 = "";
					String nameRemoved = "";
					String finalString = "";
					System.out.println("\nENTER THE NAME TO BE REMOVED");
					nameRemoved = stringReader.nextLine();
					if(!nameList.contains(nameRemoved)){
						System.out.println("\nA CONTACT WITH NAME "+nameRemoved+" DOES NOT EXIST\n");
					}
					else{
						while((line2=removalReader.readLine())!=null){
							name2 = line2.substring(0,line2.indexOf("="));
							if(!name2.equals(nameRemoved)){
								finalString = finalString+line2+System.getProperty("line.separator");
							}
						}
						BufferedWriter removalWriter = new BufferedWriter(new FileWriter(contactBookName));
						removalWriter.write(finalString);
						removalWriter.close();
						System.out.println("CONTACT "+nameRemoved+" REMOVED ");
					}
					break;
					//LISTING ELEMENTS			
				case 4: 
					int listChoice = 0;
					BufferedReader listReader = new BufferedReader(new FileReader(contactBookName));
					parts = null;
					TreeMap<String,String> nameDetailMap = new TreeMap<String,String>();
					String line4 = "";
					
					while(listChoice!=4) {
						System.out.println("\nTO DISPLAY CONTACTS BY ALPHABETICAL ORDERING OF NAMES->PRESS 1 ");
						System.out.println("TO DISPLAY CONTACTS BY CREATED DATE->PRESS 2 ");
						System.out.println("TO DISPLAY CONTACTS BY TAG->PRESS 3 ");
						System.out.println("TO GO BACK TO PREVIOUS MENU->PRESS 4 ");
						System.out.print("ENTER CHOICE::\t");
						
						while(!sc.hasNextInt()) {
							System.out.println("ENTER ONLY INTEGERS IN THE RANGE 1-4");
							sc.next();
						}	
					
						listChoice = sc.nextInt();
						
						switch(listChoice){
						//ORDERING CONTACTS BY ALPHABETICAL ORDERING OF NAMES.............. 
							case 1: 
								listReader = new BufferedReader(new FileReader(contactBookName));
								
								line4 = "";
								parts = null;
								while((line4 = listReader.readLine())!=null)
								{
									parts = line4.split("=");
									nameDetailMap.put(parts[0],parts[1]);
								}
						
								Collection<String> c = nameDetailMap.keySet();
								Iterator<String> it = c.iterator();
								if(it.hasNext()){
									System.out.println("\nCONTACTS BELOW ARE LISTED IN ALPHABETICAL ORDERING OF NAMES");
									System.out.println("-----------------------------------------------------------");
								}else{
									System.out.println("NO CONTACTS AVAILABLE");
								}
								while(it.hasNext()){
									String s = (String)it.next();
									System.out.println("\n\n"+s);
									System.out.println("---------------------------------");
									String[] contactDetail=nameDetailMap.get(s).split(":");
									for(int i=0;i<contactDetail.length;i++){
										if(contactDetail[i].trim().compareTo("")==0){
											contactDetail[i]="Unavailable";
										}
									}
									System.out.println("Date of Birth::\t"+contactDetail[0]);
									System.out.println("Pet name::\t"+contactDetail[1]);
									System.out.println("Contact Type::\t"+contactDetail[2]);
									System.out.println("Address::\t"+contactDetail[3]);
									System.out.println("Email Address::\t"+contactDetail[4]);
									System.out.println("Phone No::\t"+contactDetail[5]);
									System.out.println("Contact added::\t"+contactDetail[6]);
								}
								break;
						
						
							//ORDERING CONTACTS BY CREATED DATE.............. 
							case 2:
								BufferedReader br = new BufferedReader(new FileReader(contactBookName));
								line4 = "";
								String date = "";
								String[] part;
								TreeMap<Date,String> tm = new TreeMap<Date,String>();
								while((line4 = br.readLine())!=null){
									part = line4.split(":");
									SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd H:m");
									String h = (part[6]+":"+part[7]);
									Date d = formatter.parse(h);
									tm.put(d,line4);
				
								}
								Collection<Date> c1 = tm.keySet();
								//System.out.println(c1);
								Iterator<Date> it1 = c1.iterator();
								if(it1.hasNext()){
									System.out.println("\nCONTACTS BELOW ARE LISTED IN ORDER OF CREATED DATES");
									System.out.println("--------------------------------------------------------");
									
								}
								else{
									System.out.println("NO CONTACTS AVAILABLE");
								}
								while(it1.hasNext()){
									String s = it1.next().toString()+"";
									//System.out.println(s);
									System.out.println("\n\n"+s);
									SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd H:m");
									Date d = formatter.parse(s);
									System.out.println("---------------------------------");
									String[] nameContact=tm.get(d).split("=");
									System.out.println(nameContact[0]);
									String[] contactDetail=nameContact[1].split(":");
									for(int i=0;i<contactDetail.length;i++){
										if(contactDetail[i].trim().compareTo("")==0){
											contactDetail[i]="Unavailable";
										}
									}
									System.out.println("Date of Birth::\t"+contactDetail[0]);
									System.out.println("Pet name::\t"+contactDetail[1]);
									System.out.println("Contact Type::\t"+contactDetail[2]);
									System.out.println("Address::\t"+contactDetail[3]);
									System.out.println("Email Address::\t"+contactDetail[4]);
									System.out.println("Phone No::\t"+contactDetail[5]);
									
								}	
								break;
						
								////ORDERING CONTACTS BY TAG.............. 
								//dob:petname:tag:address:email1,email2,email3..:ph1,ph2,...:crtdate
							case 3:
								ArrayList<String> familyList = new ArrayList<String>();
								ArrayList<String> friendsList = new ArrayList<String>();
								ArrayList<String> colleaguesList = new ArrayList<String>();
								ArrayList<String> othersList = new ArrayList<String>();
								line4 = "";
								parts = null;
								br = new BufferedReader(new FileReader(contactBookName));
								while((line4=br.readLine())!=null)
								{
									parts = line4.split(":");
									String relation = parts[2];
									
									if(relation.equals("Family")){
										familyList.add(line4);
									}
									else if(relation.equals("Friend")){
										friendsList.add(line4);
									}
									else if(relation.equals("Associate")){
										colleaguesList.add(line4);
									}
									else{
										othersList.add(line4);
									}
								}
								if(familyList.isEmpty() && friendsList.isEmpty() && colleaguesList.isEmpty()
										&& othersList.isEmpty()){
									System.out.println("NO CONTACTS AVAILABLE.");
								}else{
									System.out.println("\nCONTACTS BELOW ARE LISTED ACCORDING TO TAGS");
									System.out.println("--------------------------------------------------------");
									
								}
								if(!familyList.isEmpty()){
									System.out.println("\nFAMILY CONTACTS\n----------------------------");
									for(Object s: familyList){
										String[] nameContact=s.toString().split("=");
										System.out.println(nameContact[0]+"\n");
										String[] contactDetail=nameContact[1].split(":");
										for(int i=0;i<contactDetail.length;i++){
											if(contactDetail[i].trim().compareTo("")==0){
												contactDetail[i]="Unavailable";
											}
										}
										System.out.println("   Date of Birth::\t"+contactDetail[0]);
										System.out.println("   Pet name::\t\t"+contactDetail[1]);
										System.out.println("   Address::\t\t"+contactDetail[3]);
										System.out.println("   Email Address::\t"+contactDetail[4]);
										System.out.println("   Phone No::\t\t"+contactDetail[5]);
										System.out.println("   Contact added::\t"+contactDetail[6]);
									}
								}
						
								if(!friendsList.isEmpty()){
									System.out.println("\nFRIEND CONTACTS\n----------------------------");
									for(Object s: friendsList){
										String[] nameContact=s.toString().split("=");
										System.out.println(nameContact[0]+"\n");
										String[] contactDetail=nameContact[1].split(":");
										for(int i=0;i<contactDetail.length;i++){
											if(contactDetail[i].trim().compareTo("")==0){
												contactDetail[i]="Unavailable";
											}
										}
										System.out.println("   Date of Birth::\t"+contactDetail[0]);
										System.out.println("   Pet name::\t\t"+contactDetail[1]);
										System.out.println("   Address::\t\t"+contactDetail[3]);
										System.out.println("   Email Address::\t"+contactDetail[4]);
										System.out.println("   Phone No::\t\t"+contactDetail[5]);
										System.out.println("   Contact added::\t"+contactDetail[6]);
									}
								}				
								if(!colleaguesList.isEmpty())
								{
									System.out.println("\nASSOCIATE CONTACTS\n----------------------------");
									for(Object s: colleaguesList)
									{
										String[] nameContact=s.toString().split("=");
										System.out.println(nameContact[0]+"\n");
										String[] contactDetail=nameContact[1].split(":");
										for(int i=0;i<contactDetail.length;i++){
											if(contactDetail[i].trim().compareTo("")==0){
												contactDetail[i]="Unavailable";
											}
										}
										System.out.println("   Date of Birth::\t"+contactDetail[0]);
										System.out.println("   Pet name::\t\t"+contactDetail[1]);
										System.out.println("   Address::\t\t"+contactDetail[3]);
										System.out.println("   Email Address::\t"+contactDetail[4]);
										System.out.println("   Phone No::\t\t"+contactDetail[5]);
										System.out.println("   Contact added::\t"+contactDetail[6]);
									}
								}
						
								if(!othersList.isEmpty())				
								{
									System.out.println("\nOTHER CONTACTS\n----------------------------");
									for(Object s: othersList)
									{
										String[] nameContact=s.toString().split("=");
										System.out.println(nameContact[0]+"\n");
										String[] contactDetail=nameContact[1].split(":");
										for(int i=0;i<contactDetail.length;i++){
											if(contactDetail[i].trim().compareTo("")==0){
												contactDetail[i]="Unavailable";
											}
										}
										System.out.println("   Date of Birth::\t"+contactDetail[0]);
										System.out.println("   Pet name::\t\t"+contactDetail[1]);
										System.out.println("   Address::\t\t"+contactDetail[3]);
										System.out.println("   Email Address::\t"+contactDetail[4]);
										System.out.println("   Phone No::\t\t"+contactDetail[5]);
										System.out.println("   Contact added::\t"+contactDetail[6]);
									}
								}
								break;
						
							case 4:
								break;
							default :
								System.out.println("\nENTER ONLY INTEGERS IN THE RANGE 1-5");
								break;
						}
				
					}
					break;
					//SEARCH CONTACTS BOOK...
				case 5:
					String[] details1 = null;
					line4 = "";
					searchString = "";
					parts = null;
					String addedDetails = "";
					fileReader = new BufferedReader(new FileReader(contactBookName));
					System.out.println("ENTER THE STRING TO BE SERCHED FOR IN THE CONTACTS BOOK");
					searchString = stringReader.nextLine();
					ArrayList<String> nameMatchList = new ArrayList<String>();
					ArrayList<String> emailMatchList = new ArrayList<String>();
					ArrayList<String> tagMatchList = new ArrayList<String>();
					ArrayList<String> addressMatchList = new ArrayList<String>();
					ArrayList<String> dobMatchList = new ArrayList<String>();
					ArrayList<String> phoneMatchList = new ArrayList<String>();
					ArrayList<String> petNameMatchList = new ArrayList<String>();
					String addedString = "";
					while((line4=fileReader.readLine())!=null)
					{
						parts = line4.split("=");
						name = parts[0];
						details1 = parts[1].split(":");
						dateOfBirth = details1[0];
						petName = details1[1];
						contactType = details1[2];
						address = details1[3];
						email = details1[4];
						phoneList = details1[5];
						contactAdded=details1[6];
						
						addedString = "";
						//System.out.println("name = "+name+" dateOfbirth = "+dateOfBirth+" petName ="+petName+" contactType ="+contactType+"address ="+address+"email ="+email+"phoneList ="+phoneList);
						
						if(name.contains(searchString))
						{
							addedString = name+"="+dateOfBirth+":"+petName+":"+contactType+":"+address+":"+email+":"+phoneList+":"+contactAdded;
							nameMatchList.add(addedString);
						}	
				
						if(dateOfBirth.contains(searchString))
						{
							addedString = name+"="+dateOfBirth+":"+petName+":"+contactType+":"+address+":"+email+":"+phoneList+":"+contactAdded;
							dobMatchList.add(addedString);
						}
				
						if(petName.contains(searchString))
						{
							addedString = name+"="+dateOfBirth+":"+petName+":"+contactType+":"+address+":"+email+":"+phoneList+":"+contactAdded;
							petNameMatchList.add(addedString);
						}
				
						if(contactType.contains(searchString))
						{
							addedString = name+"="+dateOfBirth+":"+petName+":"+contactType+":"+address+":"+email+":"+phoneList+":"+contactAdded;
							tagMatchList.add(addedString);
						}
				
						if(address.contains(searchString))
						{
							addedString = name+"="+dateOfBirth+":"+petName+":"+contactType+":"+address+":"+email+":"+phoneList+":"+contactAdded;
							addressMatchList.add(addedString);
						}
				
						if(email.contains(searchString))
						{
							addedString = name+"="+dateOfBirth+":"+petName+":"+contactType+":"+address+":"+email+":"+phoneList+":"+contactAdded;
							emailMatchList.add(addedString);
						}
				
						if(phoneList.contains(searchString))
						{
							addedString = name+"="+dateOfBirth+":"+petName+":"+contactType+":"+address+":"+email+":"+phoneList+":"+contactAdded;
							phoneMatchList.add(addedString);
						}	
						
					}
					System.out.println("\nTOTAL NUMBER OF MATCHES = "+(nameMatchList.size()+dobMatchList.size()+petNameMatchList.size()+tagMatchList.size()+addressMatchList.size()+emailMatchList.size()+phoneMatchList.size()));
					if(!nameMatchList.isEmpty())
					{
						System.out.println("\nTOTAL "+nameMatchList.size()+" NAMES MATCH WITh "+searchString+"\n");
						System.out.println("\nTHE NAMES OF THE FOLLOWING CONTACTS MATCHES WITH THE SEARCH STRING ");
						System.out.println("--------------------------------------------------------------------------------\n");
						
						for(Object s: nameMatchList)
						{
							showContactByType(s,2);
						}
						
					}
					if(!dobMatchList.isEmpty())
					{
						System.out.println("\nTOTAL "+dobMatchList.size()+" DOBS' MATCH WITH "+searchString+"\n");
						System.out.println("\nTHE DOB'S OF THE FOLLOWING CONTACTS MATCHES WITH THE SEARCH STRING ");
						System.out.println("--------------------------------------------------------------------------------\n");
			
						for(Object s: dobMatchList)
						{
							showContactByType(s,2);
						}
			
					}	
					if(!petNameMatchList.isEmpty())
					{
						System.out.println("\nTOTAL "+petNameMatchList.size()+" PET NAMES MATCH WITH  "+searchString+"\n");
						System.out.println("\nTHE PET NAMES OF THE FOLLOWING CONTACTS MATCHES WITH THE SEARCH STRING ");
						System.out.println("--------------------------------------------------------------------------------\n");
			
						for(Object s: petNameMatchList)
						{
							showContactByType(s,2);
						}
			
					}
					if(!tagMatchList.isEmpty())
					{
						System.out.println("\nTOTAL "+tagMatchList.size()+" CONTACT TYPES MATCH WITH THE "+searchString+"\n");
						System.out.println("\nTHE TAGS OF THE FOLLOWING CONTACTS MATCHES WITH THE SEARCH STRING ");
						System.out.println("--------------------------------------------------------------------------------\n");
			
						for(Object s: tagMatchList)
						{
							showContactByType(s,2);
						}
					}
					if(!addressMatchList.isEmpty())
					{
						System.out.println("\nTOTAL "+addressMatchList.size()+" ADDRESSES MATCH WITH "+searchString+"\n");
						System.out.println("\nTHE ADDRESSES OF THE FOLLOWING CONTACTS MATCHES WITH THE SEARCH STRING ");
						System.out.println("--------------------------------------------------------------------------------\n");
						
						for(Object s: addressMatchList)
						{
							showContactByType(s,2);
						}
					}
					if(!emailMatchList.isEmpty())
					{
						System.out.println("\nTOTAL "+emailMatchList.size()+" EMAILS MATCH WITH "+searchString+"\n");
						System.out.println("\nTHE EMAIL'S OF THE FOLLOWING CONTACTS MATCHES WITH THE SEARCH STRING ");
						System.out.println("--------------------------------------------------------------------------------\n");
			
						for(Object s: emailMatchList)
						{
							showContactByType(s,2);
						}	
					}
					if(!phoneMatchList.isEmpty())
					{
						System.out.println("\nTOTAL "+phoneMatchList.size()+" CONTACT NUMBERS MATCH WITH "+searchString+"\n");
						System.out.println("\nTHE PHONE NUMBERS OF THE FOLLOWING CONTACTS MATCHES WITH THE SEARCH STRING ");
						System.out.println("--------------------------------------------------------------------------------\n");
						
						for(Object s: phoneMatchList)
						{
							showContactByType(s,2);
						}
					}
					
					break;
				case 6:
					break;
			
				default: System.out.println("\nENTER ONLY NUMBERS  RANGED FROM 1-6");
					break;
			}
		}
		//fileReader.close();
		//fileWriter.close();
		//stringReader.close();
		//sc.close();
	} 
	public static boolean isValidDate(String dob){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date testDate = null;
		try{
			testDate = sdf.parse(dob);
		}catch (ParseException e){
			return false;
		}
		if(!sdf.format(testDate).equals(dob))
		{
			return false;
		}
		return true;
		
	}
	
	private static void listPhoneBooks(String curDirectory) throws IOException, ParseException {
		File myDir=new File(curDirectory);
		String[] filenames= myDir.list();
		ArrayList<String> phoneBooks=new ArrayList<String>();
		for(int i=0;i<filenames.length;i++){
			int temp=filenames[i].lastIndexOf(".");
			if(temp>0){
				String ext=filenames[i].substring(temp+1);
				if(ext.compareTo("txt")==0){
					phoneBooks.add(filenames[i].substring(0, temp));
				}
			}
		}
		displayPhoneBooks(phoneBooks);
		
	}





	private static void displayPhoneBooks(ArrayList<String> phoneBooks) throws IOException, ParseException {
		if(phoneBooks.size()==0){
			System.out.println("\nNO PHONEBOOK AVAILABLE.CREATE A NEW PHONEBOOK.\n");
			return;
		}else{
			System.out.println("FOLLOWING ARE THE AVAILABLE PHONEBOOKS.");
			System.out.println("----------------------------------------\n");
			Scanner sc=new Scanner(System.in);
			for(int i=0;i<phoneBooks.size();i++){
				String s=phoneBooks.get(i);
				System.out.println(s+"\t->PRESS "+(i+1)+" LOAD THIS.");
			}
			System.out.println("TO GO BACK TO THE PREVIOUS MENU->PRESS "+(phoneBooks.size()+1));
			int ch=0;
			while(ch<1 || ch>(phoneBooks.size()+1)){
				while(!sc.hasNextInt())
				{
					System.out.println("\nONLY NUMBERS LISTED ABOVE SHOULD BE ENTERED..RETRY..");
					sc.nextLine();
				}
				ch = sc.nextInt();
				if(ch<1 || ch>phoneBooks.size())
					System.out.println("\nONLY NUMBERS LISTED ABOVE SHOULD BE ENTERED..RETRY..");
			}
			if(ch==(phoneBooks.size()+1)) return;
			contactBookOperations(phoneBooks.get(ch-1)+".txt");
		}
		
	}
	private static void showContactByType(Object s,int forbid){
		String[] nameContact=s.toString().split("=");
		System.out.println(nameContact[0]+"\n");
		String[] contactDetail=nameContact[1].split(":");
		for(int i=0;i<contactDetail.length;i++){
			if(contactDetail[i].trim().compareTo("")==0){
				contactDetail[i]="Unavailable";
			}
		}
		if(forbid!=0)
			System.out.println("   Date of Birth::\t"+contactDetail[0]);
		if(forbid!=1)
			System.out.println("   Pet name::\t\t"+contactDetail[1]);
		if(forbid!=2)
			
		if(forbid!=3)
			System.out.println("   Address::\t\t"+contactDetail[3]);
		if(forbid!=4)
			System.out.println("   Email Address::\t"+contactDetail[4]);
		if(forbid!=5)
			System.out.println("   Phone No::\t\t"+contactDetail[5]);
		if(forbid!=6)
			System.out.println("   Contact added::\t"+contactDetail[6]);
	}

}
