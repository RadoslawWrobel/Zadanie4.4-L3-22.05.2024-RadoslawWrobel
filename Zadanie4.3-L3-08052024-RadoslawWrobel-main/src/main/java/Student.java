public class Student {

  private String Name;
  private String LastName;
  private int AlbumNumber;
  private int Age;
  private String DataUrodzenia; 

  public Student(String name, String lastName, int albumNumber, int age, String dataurodzenia) {
    Name = name;
    LastName = lastName;
    AlbumNumber = albumNumber;
    Age = age;
    DataUrodzenia = dataurodzenia;
  }

  public String GetName() {return Name;}
  public String GetLastName() { return LastName; }
  public int GetAlbumNumber() { return AlbumNumber; }
  public int GetAge() {return Age;}
  public String GetDataUrodzenia() {return DataUrodzenia;}

  public String ToString() {
    return Name + " " + LastName + " " + Integer.toString(AlbumNumber) + " " + Integer.toString(Age) + " " + DataUrodzenia;
  }

  public static Student Parse(String str) {
    String[] data = str.split(" ");
    if(data.length != 5)
      return new Student("Parse Error", "Parse Error", -1, -1, "00-00-0000");
    return new Student(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4]);
  }
}