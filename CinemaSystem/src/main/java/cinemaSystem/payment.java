package cinemaSystem;

public class payment {


private int payment ;
private String booking;
private String paymentmethod;
private String paymentstatus;
private double amount;
public payment(int payment, String booking, String paymentmethod, String paymentstatus, double amount) {
	super();
	this.payment = payment;
	this.booking = booking;
	this.paymentmethod = paymentmethod;
	this.paymentstatus = paymentstatus;
	this.amount = amount;
}
public int getPayment() {
	return payment;
}
public void setPayment(int payment) {
	this.payment = payment;
}
public String getBooking() {
	return booking;
}
public void setBooking(String booking) {
	this.booking = booking;
}
public String getPaymentmethod() {
	return paymentmethod;
}
public void setPaymentmethod(String paymentmethod) {
	this.paymentmethod = paymentmethod;
}
public String getPaymentstatus() {
	return paymentstatus;
}
public void setPaymentstatus(String paymentstatus) {
	this.paymentstatus = paymentstatus;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}


}
