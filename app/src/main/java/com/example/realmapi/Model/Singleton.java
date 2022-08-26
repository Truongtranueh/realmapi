package com.example.realmapi.Model;


import com.example.realmapi.Model.Product;

import java.nio.channels.UnresolvedAddressException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singleton {
    private static Retrofit retrofit = null;
    private static List<Product> listproduct;
    private static List<History> listhistory;
    private static List<User> listuser;
    private static List<Room> listroom;

    public static Retrofit getRetrofit(String baseUrl) {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static List<Product> getListproduct(){
        if(listproduct == null){
            listproduct = new ArrayList<>();
            listproduct.add(new Product(1,"Electric bill","https://png.pngtree.com/png-vector/20190826/ourlarge/pngtree-light-bulb-icon-vector-light-bulb-ideas-symbol-illustration-png-image_1701258.jpg", "Electric"));
            listproduct.add(new Product(2,"Water bill","https://static.vecteezy.com/system/resources/previews/003/062/491/original/water-utility-bill-icon-line-vector.jpg","Water"));
            listproduct.add(new Product(3,"Network registration","https://i.pinimg.com/originals/94/47/fd/9447fd1208e259e642eff25bfa11eaab.jpg","Internet"));
            listproduct.add(new Product(4,"Internet/4G Mobile","https://img.freepik.com/free-vector/phone-human-hand-3d-cartoon-style-icon-person-businessman-using-social-media-smartphone-cellphone-flat-vector-illustration-technology-communication-internet-concept_74855-26088.jpg?w=2000","Internet"));
            listproduct.add(new Product(5,"Mobile Recharge","https://p.kindpng.com/picc/s/191-1918642_mobile-recharge-icon-png-png-download-jonny-greenwood.png","Payment"));
            listproduct.add(new Product(6,"Tuition Payment","https://cdn5.vectorstock.com/i/thumb-large/08/44/education-loan-rgb-color-icon-vector-39050844.jpg","Payment"));
        }
        return  listproduct;
    }
    public static List<History> getListhistory(){
        if(listhistory == null){
            listhistory = new ArrayList<>();
            listhistory.add(new History("Receive","Receive money from TRAN VAN TRUONG", Calendar.getInstance().getTime(), 250000));
            listhistory.add(new History("Send","Send money to TRAN VAN B",Calendar.getInstance().getTime(),830000));
            listhistory.add(new History("Receive","Receive money from NGUYEN VAN A",Calendar.getInstance().getTime(),400000));
            listhistory.add(new History("Send","Send money to NGUYEN THI C",Calendar.getInstance().getTime(),920000));
        }
        return listhistory;
    }
    public static List<User> getListuser(){
        if(listuser == null){
            listuser = new ArrayList<>();
            listuser.add(new User(1,"0123","TRAN VAN TRUONG"));
            listuser.add(new User(2,"0352381030","NGUYEN VAN B"));
            listuser.add(new User(3,"03423112323","TRAN VAN C"));
            listuser.add(new User(4,"03431233323","TRAN VAN D"));
        }
        return listuser;
    }
//    public static List<Room> getListroom(){
//        if(listroom == null) {
//            listroom = new ArrayList<>();
//            listroom.add(new Room(1321, "Living Room", "https://images.adsttc.com/media/images/6202/6f99/44ba/f72b/68d1/5329/newsletter/1-romulo-fialdini.jpg?1644326817"));
//            listroom.add(new Room(2231, "Kitchen", "https://i.pinimg.com/736x/72/78/f6/7278f6ab28e82f29201b3ebe320386bb.jpg"));
//            listroom.add(new Room(3231, "Dinning Room", "https://images.unsplash.com/photo-1617806118233-18e1de247200?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZGluaW5nJTIwcm9vbXxlbnwwfHwwfHw%3D&w=1000&q=80"));
//            listroom.add(new Room(443, "Garage", "https://images.adsttc.com/media/images/60e6/1bca/f758/6e49/0202/2622/large_jpg/fi-facade-4.jpg?1625693237"));
//            listroom.add(new Room(5231, "BackYard", "https://i2.wp.com/movingtips.wpengine.com/wp-content/uploads/2021/06/backyard-ideas.jpg?fit=500%2C333&ssl=1"));
//            listroom.add(new Room(63312, "Front Yard", "https://www.thespruce.com/thmb/15dZAkrWgnRniZvfGprGYPt1B20=/1200x950/filters:no_upscale():max_bytes(150000):strip_icc()/ladylandscapefrontyard-3a7705e4550045b08448b2e3f026528b.jpg"));
//        }
//        return listroom;
//    }

}
