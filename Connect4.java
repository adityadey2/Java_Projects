import java.util.Scanner;
public class Connect4 {
	public static void main(String[] args){
		Scanner in=null;
		Scanner stringReader=null;
		
		try{
			int DIM=6;
			int no_of_players=0;
			int[][] mat=new int[DIM][DIM];
			int[] hIndicator=new int[DIM];
			String s1="",s2="";
			String player1="";
			String player2="";
			char pch1='X';
			char pch2='O';
			in=new Scanner(System.in);
			System.out.println("              CONNECT 4");
			System.out.println("-----------------------------------------------\n\n");
			while(no_of_players!=3){
				System.out.println("FOR SINGLE PLAYER->PRESS 1\n");
				System.out.println("FOR DOUBLE PLAYERS->PRESS 2\n");
				System.out.println("TO EXIT->PRESS 3\n");
				for(int i=0;i<DIM;i++){
					hIndicator[i]=0;
					for(int j=0;j<DIM;j++)
						mat[i][j]=0;
						
				}
				while(!in.hasNextInt()){
					System.out.println("NUMBERS IN THE RANGE 1-3 CAN BE PRESSED.\n");
					in.nextLine();
				}
				no_of_players=in.nextInt();
				switch(no_of_players){
					case 1:
						break;
					case 2:
						stringReader=new Scanner(System.in);
						System.out.print("ENTER THE NAME OF PLAYER 1->\t");
						s1=stringReader.nextLine();
						System.out.print("ENTER THE NAME OF PLAYER 2->\t");
						s2=stringReader.nextLine();
						System.out.println("             GAME STARTING..");
						System.out.println("------------------------------------------\n");
						System.out.println("TO MAKE a TOSS FOR WHO IS STARTING->PRESS 1\n");
						System.out.println("TO DECIDE MANUALLY->PRESS 2\n");
						int choice1=0;
						while(choice1<1 || choice1>2){
							while(!in.hasNextInt()){
								System.out.println("NUMBERS IN THE RANGE 1-2 CAN BE PRESSED.\n");
								in.nextLine();
							}
							choice1=in.nextInt();
						}
						switch(choice1){
							case 1:
								int randomChoice=(int) (Math.floor(Math.random()*10))%2;
								if(randomChoice==0){
									player1=s1;
									player2=s2;
								}else{
									player1=s2;
									player2=s1;
								}
								
								break;
							case 2:
								System.out.println("FOR "+s1+" TO START->PRESS 1\n");
								System.out.println("FOR "+s2+" TO START->PRESS 2\n");
								int choice2=0;
								while(choice2<1 || choice2>2){
									while(!in.hasNextInt()){
										System.out.println("ONLY NUMBERS IN THE RANGE 1-2 SHOULD BE PRESSED..TRY AGAIN..\n");
										in.nextLine();
									}
									choice2=in.nextInt();
								}
								if(choice2==1){
									player1=s1;
									player2=s2;
								}else{
									player1=s2;
									player2=s1;
								}
								break;
							default:
								break;
						
						}
						boolean flag=true,completion=false,coordinateCheck=false;
						int roomCount=0;
						int Cor=0;
						System.out.println(player1+" IS STARTING THE GAME.\n");
						while(roomCount<36 && completion==false){
							if(flag){
								System.out.println(player1+"  :: GIVE THE COLOMN NUMBER IN THE RANGE 1-"+DIM);
								coordinateCheck=false;
								while(!coordinateCheck){
									while(!in.hasNextInt()){
										System.out.println("ONLY NUMBERS IN THE RANGE 1-"+DIM+" SHOULD BE PRESSED..TRY AGAIN..\n");
										in.nextLine();
									}
									Cor=in.nextInt();
									if(Cor<1 || Cor>6 || mat[0][Cor-1]!=0){
										if(mat[0][Cor-1]!=0){
											System.out.println("COLOMN ALREADY OCCUPIED,SELECT A DIFFERENT COLOMN\n");
										}else{
											System.out.println("INVALID COORDINATE.GIVE THE COLOMN IN THE RANGE 1-"+DIM);
										}
									}else{
										mat[DIM-1-hIndicator[Cor-1]][Cor-1]=1;
										hIndicator[Cor-1]++;
										coordinateCheck=true;
										roomCount+=1;
									}
								}
								flag=!flag;
							}else{
								System.out.println(player2+"  :: GIVE THE COLOMN NUMBER IN THE RANGE 1-"+DIM);
								coordinateCheck=false;
								while(!coordinateCheck){
									while(!in.hasNextInt()){
										System.out.println("ONLY NUMBERS IN THE RANGE 1-"+DIM+" SHOULD BE PRESSED..TRY AGAIN..\n");
										in.nextLine();
									}
									Cor=in.nextInt();
									if(Cor<1 || Cor>6 || mat[0][Cor-1]!=0){
										if(mat[0][Cor-1]!=0){
											System.out.println("COLOMN ALREADY OCCUPIED,SELECT A DIFFERENT COLOMN\n");
										}else{
											System.out.println("INVALID COORDINATE.GIVE THE COLOMN IN THE RANGE 1-"+DIM);
										}
									}else{
										mat[DIM-1-hIndicator[Cor-1]][Cor-1]=2;
										hIndicator[Cor-1]++;
										coordinateCheck=true;
										roomCount+=1;
									}
								}
								flag=!flag;
							}
							if(horizontal(mat,Cor,hIndicator,DIM)){
								completion=true;
							}
							if(!completion){
								if(vertical(mat,Cor,hIndicator,DIM)){
									completion=true;
								}
							}
							if(!completion){
								if(diagonal(mat,Cor,hIndicator,DIM)){
									completion=true;
								}
							}
							if(completion){
								if(!flag){
									System.out.println(player1 + " HAS BEATEN " + player2 + " IN THIS GAME.\n");
								}else{
									System.out.println(player2 + " HAS BEATEN " + player1 + " IN THIS GAME.\n");
								}
							}
							System.out.println("\n");
							for(int i=0;i<DIM;i++){
								for(int j=0;j<DIM;j++){
									if(mat[i][j]==1)	System.out.print(pch1);
									else if(mat[i][j]==2)	System.out.print(pch2);
									else System.out.print('_');
									System.out.print("  ");
								}
								System.out.println("\n");
							}
							System.out.println("\n");
						}
						if(roomCount>=9 && !completion){
							System.out.println("MATCH HAS BEEN DRAWN..NO ROOM IS LEFT TO MARK.\n\n");
						}
						break;
					case 3:
						break;
					default:
						System.out.println("NUMBERS IN THE RANGE 1-3 CAN BE PRESSED.\n");
						break;
				}
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static boolean diagonal(int[][] mat, int Cor, int[] hIndicator, int DIM) {
		
		int temp;
		int hor=(Cor>3)?Math.min(4, DIM-Cor+1):Cor;
		int k=Math.min(hor, hIndicator[Cor-1]+1);
		int hCor=DIM-hIndicator[Cor-1];
		boolean b=(Cor>3)?false:true;
		boolean isResult=true;
		for(int i=0;i<k;i++){
			int m;
			isResult=true;
			m=Cor-k+i;
			temp=mat[hCor-k+(i+1)][m];
			if(temp!=0){
			int rCount=0;
			for(int j=1;j<4;j++){
				if(j+m<DIM && hCor-k+(i+1)+j<DIM ){
					if(temp!=mat[hCor-k+(i+1)+j][j+m]){
						isResult=false;
						break;
					}
					rCount++;
				}
			}
			if(rCount==3 && isResult)
				return true;
			}
			
		}
		for(int i=0;i<k;i++){
			int m;
			isResult=true;
			m=Cor+k-i-1;
			if(m<DIM){
			temp=mat[hCor-k+(i+1)][m];
			if(temp!=0){
			int rCount=0;
			for(int j=1;j<4;j++){
				if(m-j>=0 && hCor-k+(i+1)+j<DIM ){
					if(temp!=mat[hCor-k+(i+1)+j][m-j]){
						isResult=false;
						break;
					}
					rCount++;
				}
			}
			if(rCount==3 && isResult)
				return true;
			}
			}
			
		}
		return false;
	}

	private static boolean vertical(int[][] mat, int Cor, int[] hIndicator, int DIM) {
		if(hIndicator[Cor-1]>=4){
			int hCor=DIM-hIndicator[Cor-1];
			if(mat[hCor][Cor-1]!=0 && mat[hCor][Cor-1]==mat[hCor+1][Cor-1] 
				&& mat[hCor][Cor-1]==mat[hCor+2][Cor-1] &&
				mat[hCor][Cor-1]==mat[hCor+3][Cor-1])	return true;
		}
		return false;
	}

	private static boolean horizontal(int[][] mat, int Cor, int[] hIndicator, int DIM) {
		int temp;
		int k=(Cor>3)?Math.min(4, DIM-Cor+1):Cor;
		boolean b=(Cor>3)?false:true;
		boolean isResult=true;
		int hCor=DIM-hIndicator[Cor-1];
		for(int i=0;i<k;i++){
			int m;
			isResult=true;
			if(b)
				m=i;
			else
				m=Cor+i-4;
			temp=mat[hCor][m];
			if(temp!=0){
			for(int j=m+1;j<m+4;j++){
				if(temp!=mat[hCor][j]){
					isResult=false;
					break;
				}
			}
			if(isResult){
				return true;
			}
			}
		}
		return false;
	}
	
	
}
