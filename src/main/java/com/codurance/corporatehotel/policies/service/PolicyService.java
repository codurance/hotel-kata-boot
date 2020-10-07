package com.codurance.corporatehotel.policies.service;

import com.codurance.corporatehotel.common.model.RoomTypes;

import java.util.List;

public interface PolicyService {

    void setEmployeePolicy(Integer employeeId, List<RoomTypes> roomTypes);

    void setCompanyPolicy(Integer companyId, List<RoomTypes> roomTypes);

    boolean isBookingAllowed(Integer employeeId, RoomTypes roomType);

}
