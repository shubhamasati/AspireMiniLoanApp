package com.aspire.loan.service.impl;

import com.aspire.loan.constants.Constants;
import com.aspire.loan.enums.CommonError;
import com.aspire.loan.enums.LoanApplicationStatus;
import com.aspire.loan.exceptions.ApplicationException;
import com.aspire.loan.model.entity.LoanApplicationEntity;
import com.aspire.loan.model.entity.LoanEntity;
import com.aspire.loan.model.entity.UserEntity;
import com.aspire.loan.repository.LoanApplicationRepository;
import com.aspire.loan.repository.LoanEntityRepository;
import com.aspire.loan.service.LoanService;
import com.aspire.loan.service.UserService;
import com.aspire.loan.utils.SecurityUtil;
import com.aspire.model.ErrorDetails;
import com.aspire.model.LoanApplication;
import com.aspire.model.LoanApplicationResponse;
import com.aspire.model.LoanApprovalResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceImplTest {

    @Mock
    private LoanApplicationRepository loanApplicationRepository;
    @Mock
    private LoanEntityRepository loanEntityRepository;
    @Mock
    private UserService userService;
    @Mock
    private LoanService loanService;
    @InjectMocks
    private LoanApplicationServiceImpl underTest;


    @Nested
    class WhenCreatingApplication {
        private LoanApplication loanApplication;

        @BeforeEach
        void setup() {
            loanApplication = new LoanApplication();
            loanApplication.setAmount("100");
            loanApplication.setTerm(3);
        }

        @Test
        public void shouldFailWhenRequestIsNull() {
            loanApplication = null;
            ApplicationException errorResponse = catchThrowableOfType(
                    () -> underTest.createApplication(loanApplication), ApplicationException.class);
            assertThat(errorResponse)
                    .isNotNull()
                    .hasMessage(CommonError.BAD_REQUEST.getMessage());
            assertThat(errorResponse.getError())
                    .isNotNull();
            assertThat(errorResponse.getError().getDetails())
                    .isNotNull()
                    .hasSize(1);

            ErrorDetails errorDetails = errorResponse.getError().getDetails().get(0);
            assertThat(errorDetails)
                    .isNotNull();
            assertThat(errorDetails)
                    .extracting(Constants.ERROR_PROPERTY_ISSUE)
                    .isEqualTo(CommonError.INVALID_REQUEST.getName());
            assertThat(errorDetails)
                    .extracting(Constants.ERROR_PROPERTY_DESCRIPTION)
                    .isEqualTo(CommonError.INVALID_REQUEST.getMessage());


        }

        @Test
        public void shouldFailWhenInvalidAmountValueIsPassed() {
            loanApplication.setAmount("-10000");
            ApplicationException errorResponse = catchThrowableOfType(
                    () -> underTest.createApplication(loanApplication), ApplicationException.class);
            assertThat(errorResponse)
                    .isNotNull()
                    .hasMessage(CommonError.BAD_REQUEST.getMessage());
            assertThat(errorResponse.getError())
                    .isNotNull();
            assertThat(errorResponse.getError().getDetails())
                    .isNotNull()
                    .hasSize(1);

            ErrorDetails errorDetails = errorResponse.getError().getDetails().get(0);
            assertThat(errorDetails)
                    .isNotNull();
            assertThat(errorDetails)
                    .extracting(Constants.ERROR_PROPERTY_ISSUE)
                    .isEqualTo(CommonError.INVALID_PARAMETER_VALUE.getName());
            assertThat(errorDetails)
                    .extracting(Constants.ERROR_PROPERTY_DESCRIPTION)
                    .isEqualTo(CommonError.INVALID_PARAMETER_VALUE.getMessage());
        }

        @Test
        public void shouldCreateApplicationSuccessfullyWhenCorrectDataPassed() {
            MockedStatic<SecurityUtil> securityUtilMockedStatic = mockStatic(SecurityUtil.class);
            securityUtilMockedStatic.when(SecurityUtil::getUsername).thenReturn("user");

            LoanApplicationEntity loanApplicationEntity = new LoanApplicationEntity();
            loanApplicationEntity.setId(1L);
            when(loanApplicationRepository.save(any())).thenReturn(loanApplicationEntity);
            LoanApplicationResponse response = underTest.createApplication(loanApplication);
            assertThat(response)
                    .isNotNull();
            assertThat(response.getApplicationId())
                    .isNotNull();
            assertThat(response.getStatus())
                    .isEqualTo("APPLICATION_SUBMITTED");
        }
    }

    @Nested
    class WhenApprovingLoanApplication {
        @BeforeEach
        void setup() {
        }

        @Test
        public void shouldFailWhenInvalidApplicationIdIsPassed() {
            Long appId = -100L;

            ApplicationException errorResponse = catchThrowableOfType(
                    () -> underTest.approveLoanApplication(appId), ApplicationException.class);
            assertThat(errorResponse)
                    .isNotNull()
                    .hasMessage(CommonError.BAD_REQUEST.getMessage());
            assertThat(errorResponse.getError())
                    .isNotNull();
            assertThat(errorResponse.getError().getDetails())
                    .isNotNull()
                    .hasSize(1);

            ErrorDetails errorDetails = errorResponse.getError().getDetails().get(0);
            assertThat(errorDetails)
                    .isNotNull();
            assertThat(errorDetails)
                    .extracting(Constants.ERROR_PROPERTY_ISSUE)
                    .isEqualTo(CommonError.INVALID_REQUEST.getName());
            assertThat(errorDetails)
                    .extracting(Constants.ERROR_PROPERTY_DESCRIPTION)
                    .isEqualTo(CommonError.INVALID_REQUEST.getMessage());
        }

        @Test
        public void shouldFailWhenApplicationIdIsNull() {
            Long appId = null;

            ApplicationException errorResponse = catchThrowableOfType(
                    () -> underTest.approveLoanApplication(appId), ApplicationException.class);
            assertThat(errorResponse)
                    .isNotNull()
                    .hasMessage(CommonError.BAD_REQUEST.getMessage());
            assertThat(errorResponse.getError())
                    .isNotNull();
            assertThat(errorResponse.getError().getDetails())
                    .isNotNull()
                    .hasSize(1);

            ErrorDetails errorDetails = errorResponse.getError().getDetails().get(0);
            assertThat(errorDetails)
                    .isNotNull();
            assertThat(errorDetails)
                    .extracting(Constants.ERROR_PROPERTY_ISSUE)
                    .isEqualTo(CommonError.INVALID_REQUEST.getName());
            assertThat(errorDetails)
                    .extracting(Constants.ERROR_PROPERTY_DESCRIPTION)
                    .isEqualTo(CommonError.INVALID_REQUEST.getMessage());
        }

        @Test
        public void shouldPassWhenValidApplicationIdIsProvided() {
            Long appId = 1234L;
            LoanApplicationEntity entity = new LoanApplicationEntity();
            entity.setId(1L);
            entity.setAmount("1234");
            entity.setTerm(3);
            entity.setStatus(LoanApplicationStatus.PENDING);

            when(loanApplicationRepository.findById(anyLong()))
                    .thenReturn(Optional.of(entity));

            LoanApplicationEntity loanApplicationEntity = new LoanApplicationEntity();
            loanApplicationEntity.setId(1L);
            loanApplicationEntity.setAmount("100");
            loanApplicationEntity.setTerm(3);
            when(loanApplicationRepository.save(any())).thenReturn(loanApplicationEntity);

            LoanEntity loanEntity = new LoanEntity();
            loanEntity.setId(13L);

            UserEntity user = new UserEntity();
                    user.setId(123L);
                    user.setUserName("hank");
            loanEntity.setUser(user);
            when(loanEntityRepository.save(any())).thenReturn(loanEntity);
            when(loanService.createRepayments(any()))
                    .thenReturn(new ArrayList<>());

            LoanApprovalResponse loanApprovalResponse = underTest.approveLoanApplication(appId);
            assertThat(loanApprovalResponse)
                    .isNotNull();
        }
    }
}