package com.railway_booking.railwaybooking.entity;


import jakarta.persistence.*;

@Entity
@Table (
        name="seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_seat_number",
                        columnNames = {"coach_id", "seat_number"}
                )
        }
)
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seat_id")
    private Long seatId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="coach_id", nullable = false)
    private  Coach coach;

    @Column(name="seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name="seat_type", nullable = false)
    private String seatType;

    @Column(name = "status", nullable = false)
    private String status;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
