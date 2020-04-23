/* Date.java */

import java.io.*;

class Date {


  /* Put your private data fields here. */
    public final static int JAN = 1;
    public final static int FEB = 2;
    public final static int MAR = 3;
    public final static int APR = 4;
    public final static int MAY = 5;
    public final static int JUN = 6;
    public final static int JUL = 7;
    public final static int AUG = 8;
    public final static int SEP = 9;
    public final static int OCT = 10;
    public final static int NOV = 11;
    public final static int DEC = 12;
    private int y_;
    private int m_;
    private int d_;
    private int days_;

    /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
        if (!isValidDate(month,day,year)){
            System.out.println("format of date is wrong");
            System.exit(0);
        }
        y_ = year;
        m_ = month;
        d_ = day;
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
      String[] tmp = s.split("/");

      if (!isValidDate(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2]))){
          System.out.println("format of date is wrong");
          System.exit(0);
      }
      y_ = Integer.parseInt(tmp[2]);
      m_ = Integer.parseInt(tmp[0]);
      d_ = Integer.parseInt(tmp[1]);
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0)
            return true;
        else if (year % 400 == 0)
            return true;
        return false;
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    switch (month){
        case FEB:
            return isLeapYear(year)?29:28;
        case APR:
        case JUN:
        case SEP:
        case NOV:
            return 30;
        default:
            return 31;
    }
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
      if (year < 0)
          return false;
      return day <= daysInMonth(month,year);
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    return m_+"/"+d_+"/"+y_;
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
        if (y_ < d.y_)
            return true;
        else if (y_ > d.y_)
            return false;
        else if (m_ < d.m_)
            return true;
        else if (m_ > d.m_)
            return false;
        else if (d_ < d.d_)
            return true;
        else
            return false;
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
        if (isBefore(d) || (y_ == d.y_ && m_==d.m_&&d_==d.d_))
            return false;
        return true;

  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
        for (int i = 1;i<=m_;i++){
            days_+= daysInMonth(i,y_);
        }
        return days_;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
      return 0;
  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));
//
//    System.out.println("\nTesting difference.");
//    System.out.println(d1 + " - " + d1  + " should be 0: " +
//                       d1.difference(d1));
//    System.out.println(d2 + " - " + d1  + " should be 1: " +
//                       d2.difference(d1));
//    System.out.println(d3 + " - " + d1  + " should be 2: " +
//                       d3.difference(d1));
//    System.out.println(d3 + " - " + d4  + " should be -422: " +
//                       d3.difference(d4));
//    System.out.println(d5 + " - " + d4  + " should be 48762: " +
//                       d5.difference(d4));
  }
}
