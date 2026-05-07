package cinemaSystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Bookings")
public class BookingsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // DATABASE CONNECTION
    private static final String DB_URL ;
    private static final String USER;
    private static final String PASSWORD;

    public BookingsServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

                        HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("user") == null) {

                out.println("<h3>Please login first.</h3>");
                return;
            }

                        String userIdParam = request.getParameter("userId");
            String showtimeIdParam = request.getParameter("showtimeId");
            String totalPriceParam = request.getParameter("totalPrice");

            String[] seatArray = request.getParameterValues("seats");

                       if (userIdParam == null || userIdParam.isEmpty() ||
                showtimeIdParam == null || showtimeIdParam.isEmpty() ||
                totalPriceParam == null || totalPriceParam.isEmpty()) {

                out.println("<h3>Missing required data.</h3>");
                return;
            }

            if (seatArray == null || seatArray.length == 0) {

                out.println("<h3>No seats selected.</h3>");
                return;
            }

                       int userId = Integer.parseInt(userIdParam);

            int showtimeId = Integer.parseInt(showtimeIdParam);

            double totalPrice = Double.parseDouble(totalPriceParam);

            List<Integer> seatIds = new ArrayList<>();

            for (String seat : seatArray) {

                seatIds.add(Integer.parseInt(seat));
            }

                       Connection conn = DriverManager.getConnection(
                    DB_URL,
                    USER,
                    PASSWORD
            );

            
            ReservationController reservationController =
                    new ReservationController(conn);

            // CHECK EVERY SEAT
            for (int seatId : seatIds) {

                boolean available =
                        reservationController.checkSeatAvailability(
                                showtimeId,
                                seatId
                        );

                if (!available) {

                    out.println("<h3>Seat "
                            + seatId
                            + " is already booked.</h3>");

                    conn.close();
                    return;
                }
            }

                                   Booking booking = new Booking();

            booking.setUserId(userId);

            booking.setShowtimeId(showtimeId);

            booking.setBookingTime(java.time.LocalDateTime.now());

            booking.setTotalPrice(totalPrice);

            booking.setSeatIds(seatIds);

                        out.println("<html>");
            out.println("<body>");

            out.println("<h2>Booking Successful ✅</h2>");

            out.println("<p>User ID: "
                    + booking.getUserId()
                    + "</p>");

            out.println("<p>Showtime ID: "
                    + booking.getShowtimeId()
                    + "</p>");

            out.println("<p>Booking Time: "
                    + booking.getBookingTime()
                    + "</p>");

            out.println("<p>Total Price: "
                    + booking.getTotalPrice()
                    + "</p>");

            out.println("<p>Seats: "
                    + booking.getSeatIds()
                    + "</p>");

            out.println("</body>");
            out.println("</html>");

            conn.close();

        }

        catch (NumberFormatException e) {

            out.println("<h3>Invalid number format.</h3>");
        }

        catch (Exception e) {

            out.println("<h3>Something went wrong.</h3>");

            e.printStackTrace();
        }
    }
}