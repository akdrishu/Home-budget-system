/*  HOME BUDGET MANAGEMENT SYSTEM
*   NAME: AYUSH DWIVEDI
*   SECTION: K18KK
*   ROLL: 31
*   REG. NO:  11801780
*/


import java.util.*;

class Home {
    Scanner sc = new Scanner(System.in);
    int number;         // no of family members
    int electricity_bill;   // electricity bill
    int telephone_bill;     // telephone bill
    int grocery;            // grocery bill
    int working_no;     // no of working family members
    int nonworking_no; // no of non working member
    static int x = 0;
    static int y = 0;

    public void get_input() {       // method to take input

        System.out.println("No of members in the family?");
        number = sc.nextInt();
        System.out.println("What is the monthly electricity bill? ");
        electricity_bill = sc.nextInt();
        System.out.println("What is the monthly telephone bill? ");
        telephone_bill = sc.nextInt();
        System.out.println("What is the monthly grocery bill? ");
        grocery = sc.nextInt();
        System.out.println("No of working members?");
        working_no = sc.nextInt();
        nonworking_no = number - working_no;
    }

    class Working_Member {                                  // Nested class1
        String name;        // name of family members
        int age;            // age of member
        int salary;        // salary of working family member
        int savings;    // expected minimum amount of savings of working family member

        public void get_input() {                   // method to take input
            System.out.println("Name of " + (++x) + " working member?");
	    sc.nextLine();	
            name = sc.nextLine();
            System.out.println("Age of " + (x) + " working member?");
            age = sc.nextInt();
            System.out.println("Salary of " + (x) + " working member?");
            salary = sc.nextInt();
            System.out.println("Minimum amount of savings expected?");
            savings = sc.nextInt();
        }
    }

    class Nonworking_Member {                               // Nested class2
        String name;        // name of family members
        int age;            // age of member
        int pocketmoney;        // pocketmoney of kid
        int priorsavings;    // prior savings of non-working family member

        public void get_input() {                       //method to take input

            System.out.println("Name of " + (++y) + " non-working family member?");
            sc.nextLine();
	    name = sc.nextLine();
            System.out.println("Age of " + (y) + " non-working family member?");
            age = sc.nextInt();
            if (age <= 18) {
                System.out.println("Pocketmoney amount?");
                pocketmoney = sc.nextInt();
            } else {
                System.out.println("Any prior savings?");
                priorsavings = sc.nextInt();
            }
        }
    }

}

class Main {

    public static void main(String[] args) {
        Home home = new Home();
        home.get_input();
        Home.Working_Member[] workingmember = new Home.Working_Member[home.working_no];
        Home.Nonworking_Member[] nonworkingmember = new Home.Nonworking_Member[home.nonworking_no];
        for (int i = 0; i < home.working_no; ++i) {
            workingmember[i] = home.new Working_Member();
            workingmember[i].get_input();
        }

        for (int i = 0; i < home.nonworking_no; ++i) {
            nonworkingmember[i] = home.new Nonworking_Member();
            nonworkingmember[i].get_input();
        }
        float total_expenditure = (float) (home.electricity_bill + home.telephone_bill + home.grocery);
        System.out.println("Total expenditure is equal to: " + total_expenditure);
        try {
            System.out.println("Working members are expected to bear monthly expense. ");
            float sum_final = 0;
            int per_workingmember = 1;
            while (true) {
                System.out.println("Each working family member is expected to contribute " +  (float)(per_workingmember) * (100 / home.working_no) + "% off their salaries. ");
                float sum = 0;
                for (int i = 0; i < home.working_no; ++i) {
                    sum += (float) (per_workingmember) / (home.working_no) * workingmember[i].salary;
                }
                sum_final = sum;
                if (sum >= total_expenditure) {
                    System.out.println("Expenditure are bourne by working members,left money will be distributed equally among working members!");
                    System.out.println("Left money: " + (sum - total_expenditure) + " .Hence dividng equally, share of all:   " + (sum - total_expenditure) / home.working_no + "\nDetails:");
                    for (int i = 0; i < home.working_no; i++) {
                        System.out.println("Name:   " + workingmember[i].name);
                        System.out.println("Age:     " + workingmember[i].age);
                        System.out.println("Original salary:    " + workingmember[i].salary);
                        System.out.println("Amount contributed:  " + (float)per_workingmember / home.working_no * workingmember[i].salary);
                        System.out.println("Expected savings initially: " + workingmember[i].savings);
                        System.out.println("Updated savings:    " + (workingmember[i].salary - (float)per_workingmember / home.working_no * workingmember[i].salary + (float)(sum - total_expenditure) / home.working_no));
                        if (workingmember[i].savings <= (workingmember[i].salary - (float)per_workingmember / home.working_no * workingmember[i].salary + (float)(sum - total_expenditure) / home.working_no)) {
                            System.out.println("Congrats! Your expectations were met");
                        } else System.out.println("Couldn't meet your expectations, try next month! ");
                    }
                    break;
                }
                ++per_workingmember;
                if (per_workingmember > home.working_no) {
                    System.out.println("Sorry expenses couldn't be met by working members, need contributions from non working members also!");
                    int percentage_no = 1;
                    while (true) {
                        System.out.println("Each non working family member is expected to contribute " + (float) (percentage_no) * 100 / home.nonworking_no + "% off their savings. ");
                        float asum = 0;
                        for (int i = 0; i < home.nonworking_no; ++i) {
                            if (nonworkingmember[i].age <= 18)
                                asum += (float) (percentage_no) / (home.nonworking_no) * nonworkingmember[i].pocketmoney;
                            else
                                asum += (float) (percentage_no) / (home.nonworking_no) * nonworkingmember[i].priorsavings;
                        }
                        asum += sum_final;
                        if (asum >= total_expenditure) {
                            System.out.println("Expenditure are bourne by both working and non-working member,left money will be distributed equally among non working members only for their savings and pocketmoney!");
                            System.out.println("Left money: " + (asum - total_expenditure) + " .Hence dividng equally, share of all:   " + (float)(asum - total_expenditure) / home.nonworking_no + "\nDetails of everyone:");
                            for (int i = 0; i < home.working_no; ++i) {
                                System.out.println("Name:   " + workingmember[i].name);
                                System.out.println("Age:     " + workingmember[i].age);
                                System.out.println("Original salary:    " + workingmember[i].salary);
                                System.out.println("Amount contributed:  " + workingmember[i].salary);
                                System.out.println("Expected savings initially: " + workingmember[i].savings);
                                System.out.println("Updated savings:    0.\nCouldn't meet your expectations, try next month! ");

                            }
                            for (int i = 0; i < home.nonworking_no; i++) {
                                System.out.println("Name:   " + nonworkingmember[i].name);
                                System.out.println("Age:     " + nonworkingmember[i].age);
                                if (nonworkingmember[i].age <= 18) {
                                    System.out.println("Original pocketmoney:    " + nonworkingmember[i].pocketmoney);
                                    System.out.println("Amount contributed:  " + (float) (percentage_no)  / home.nonworking_no * nonworkingmember[i].pocketmoney);
                                    System.out.println("Updated pocketmoney:    " + (nonworkingmember[i].pocketmoney - (float) (percentage_no) / home.nonworking_no * nonworkingmember[i].pocketmoney + (float)(asum - total_expenditure) / home.nonworking_no));
                                } else {
                                    System.out.println("Original savings:    " + nonworkingmember[i].priorsavings);
                                    System.out.println("Amount contributed:  " + (float) (percentage_no) / home.nonworking_no * nonworkingmember[i].priorsavings);
                                    System.out.println("Updated savings:    " + (nonworkingmember[i].priorsavings - (float) (percentage_no) / home.nonworking_no * nonworkingmember[i].priorsavings + (float)(asum - total_expenditure) / home.nonworking_no));
                                }
                            }
                            break;
                        }
                        ++percentage_no;
                        if (percentage_no > home.nonworking_no) {
                            System.out.println("Sorry expenses couldn't be met this month :(");
                            break;
                        }
                        System.out.println("Since expenses are not met, hence contribution need to be increased.");
                    }

                    break;
                }
                System.out.println("Since expenses are not met, hence contribution need to be increased.");
            }


        } catch (Exception e) {     //division by zero error as no of working members=0
            System.out.println("Since there are no working members currently, we dwell upon prior savings of non working member. ");
            int percentage_no = 1;
            while (true) {
                System.out.println("Each non working family member is expected to contribute " + (float) (percentage_no) * 100 / home.nonworking_no + "% off their savings. ");
                float sum = 0;
                for (int i = 0; i < home.nonworking_no; ++i) {
                    if (nonworkingmember[i].age <= 18)
                        sum += (float) (percentage_no) / (home.nonworking_no) * nonworkingmember[i].pocketmoney;
                    else
                        sum += (float) (percentage_no) / (home.nonworking_no) * nonworkingmember[i].priorsavings;
                }
                if (sum >= total_expenditure) {
                    System.out.println("Expenditure are bourne by non working member,left money will be distributed equally among non working members!");
                    System.out.println("Left money: " + (sum - total_expenditure) + " .Hence dividng equally, share of all:   " + (float)(sum - total_expenditure) / home.nonworking_no + "\nDetails:");
                    for (int i = 0; i < home.nonworking_no; i++) {
                        System.out.println("Name:   " + nonworkingmember[i].name);
                        System.out.println("Age:     " + nonworkingmember[i].age);
                        if (nonworkingmember[i].age <= 18) {
                            System.out.println("Original pocketmoney:    " + nonworkingmember[i].pocketmoney);
                            System.out.println("Amount contributed:  " + (float) (percentage_no) / home.nonworking_no * nonworkingmember[i].pocketmoney);
                            System.out.println("Updated pocketmoney:    " + (nonworkingmember[i].pocketmoney - (float) (percentage_no) / home.nonworking_no * nonworkingmember[i].pocketmoney +(float)(sum - total_expenditure) / home.nonworking_no));
                        } else {
                            System.out.println("Original savings:    " + nonworkingmember[i].priorsavings);
                            System.out.println("Amount contributed:  " + (float) (percentage_no) / home.nonworking_no * nonworkingmember[i].priorsavings);
                            System.out.println("Updated savings:    " + (nonworkingmember[i].priorsavings - (float) (percentage_no) / home.nonworking_no * nonworkingmember[i].priorsavings + (float) (sum - total_expenditure) / home.nonworking_no));
                        }
                    }
                    break;
                }
                ++percentage_no;
                if (percentage_no > home.nonworking_no) {
                    System.out.println("Sorry expenses couldn't be met this month :(");
                    break;
                }
                System.out.println("Since expenses are not met, hence contribution need to be increased.");
            }
        }
    }
}




