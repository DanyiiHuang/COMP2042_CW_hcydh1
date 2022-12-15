package com.example.comp2042huangdanyi.data;

import java.util.ArrayList;


/**Class to allow user enter name,score comparing score between accounts and store into ArrayList.
 * @author DanyiHuang-modified
 */
public class Account implements Comparable<Account> {
    private long score = 0;
    private String userName ;
    private static ArrayList<Account> accounts = new ArrayList<>();

    /** Constructor for account.
     * @param userName user's name.
     */
    public Account(String userName){
        this.userName=userName;
    }

    /** Method to compare score between accounts.
     * @param o the object to be compared.
     * @return score after comparing if previous score is larger than next score.
     */
    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    /** Getter for score.
     * @return score.
     */
    private long getScore() {
        return score;
    }

    /** Getter for username.
     * @return username.
     */
    private String getUserName() {
        return userName;
    }

    static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;

    }

    /** Method to create a new account for user.
     * @param userName user's name.
     * @return latest account.
     */
    static Account makeNewAccount(String userName){
        Account account = new Account(userName);
        accounts.add(account);
        return account;
    }

}