
package com.boot.service;

import java.util.Map;

public class PredefinedAnswers {

    private static final Map<String, String> ANSWERS = Map.of(


            "upi", """
                    UPI - UNIFIED PAYMENTS INTERFACE
                    
                    1. INTRODUCTION
                    
                    UPI stands for Unified Payments Interface.
                    
                    It is an instant real-time payment system developed by
                    NPCI (National Payments Corporation of India).
                    
                    It allows customers to transfer money between different
                    bank accounts using a mobile application.
                    
                    Customers do not need to enter complete bank account
                    details for every transaction.
                    
                    UPI supports both person-to-person (P2P) and
                    person-to-merchant (P2M) payments.
                    
                    2. IMPORTANT PARTICIPANTS IN UPI
                    
                    1. NPCI:
                       National Payments Corporation of India operates the
                       UPI network and facilitates interbank transactions.
                    
                    2. Remitter Bank:
                       The bank from which the customer's account is debited.
                    
                    3. Beneficiary Bank:
                       The bank where the receiver's account is credited.
                    
                    4. PSP:
                       Payment Service Provider that facilitates UPI services.
                    
                    5. TPAP:
                       Third Party Application Provider that provides
                       UPI-enabled applications to customers.
                    
                    6. Customer:
                       The payer or beneficiary who uses UPI services.
                    
                    3. HOW UPI WORKS
                    
                    Consider an example:
                    
                    Ravi wants to transfer Rs. 5,000 to Amit using UPI.
                    
                    Step 1: Ravi opens a UPI-enabled application.
                    
                    Step 2: Ravi enters Amit's UPI ID or scans his QR code.
                    
                    Step 3: Ravi enters Rs. 5,000 as the payment amount.
                    
                    Step 4: Ravi authorizes the transaction using his UPI PIN.
                    
                    Step 5: The payment request is routed through the UPI network.
                    
                    Step 6: The remitter bank processes the debit.
                    
                    Step 7: The beneficiary bank processes the credit.
                    
                    Step 8: The transaction response is communicated to
                            the participating systems.
                    
                    4. TYPES OF UPI TRANSACTIONS
                    
                    1. P2P (Person to Person):
                       Money transfer from one individual to another individual.
                    
                    2. P2M (Person to Merchant):
                       Payment made by a customer to a merchant.
                    
                    3. QR Code Payment:
                       Customer scans a QR code and makes a payment.
                    
                    4. UPI Collect:
                       A payment request is sent to a customer for approval.
                    
                    5. UPI AutoPay:
                       Supports recurring payments through approved mandates.
                    
                    5. IMPORTANT UPI TERMINOLOGIES
                    
                    1. VPA:
                       Virtual Payment Address, commonly known as UPI ID.
                    
                    2. RRN:
                       Retrieval Reference Number used to trace transactions.
                    
                    3. UPI Transaction ID:
                       Unique transaction identifier assigned in the UPI flow.
                    
                    4. UPI PIN:
                       Secret PIN used by the customer to authorize payments.
                    
                    5. NPCI:
                       National Payments Corporation of India.
                    
                    6. PSP:
                       Payment Service Provider.
                    
                    7. TPAP:
                       Third Party Application Provider.
                    
                    6. KEY FEATURES OF UPI
                    
                    1. Instant real-time payments.
                    2. Available 24x7, including bank holidays.
                    3. Supports P2P and P2M payments.
                    4. Supports QR-code payments.
                    5. Supports multiple bank accounts through UPI applications.
                    6. Supports UPI AutoPay mandates.
                    7. Provides transaction reference details.
                    8. Reduces the need to share bank account details
                       with every payment recipient.
                    
                    7. UPI SECURITY
                    
                    UPI transactions use authentication and security controls.
                    
                    Important security aspects include:
                    
                    1. UPI PIN authentication.
                    2. Device and application security.
                    3. Secure communication between participating systems.
                    4. Transaction monitoring and fraud prevention controls.
                    
                    IMPORTANT:
                    A customer should never share their UPI PIN or OTP.
                    A UPI PIN is generally required to authorize a payment,
                    not to receive money.
                    
                    8. UPI TRANSACTION STATUS
                    
                    Common transaction states include:
                    
                    SUCCESS:
                    Transaction completed successfully.
                    
                    FAILED:
                    Transaction was unsuccessful.
                    
                    PENDING:
                    Final transaction status is not yet confirmed.
                    
                    REVERSED:
                    A reversal has been processed for the transaction.
                    
                    The exact status codes and lifecycle depend on
                    the applicable system and transaction flow.
                    
                    9. UPI RECONCILIATION
                    
                    UPI reconciliation is the process of matching transaction
                    records between the bank's internal system and applicable
                    external transaction and settlement records.
                    
                    Reconciliation steps:
                    
                    1. Receive transaction and reconciliation files.
                    2. Fetch files through configured SFTP channels.
                    3. Validate file format and business date.
                    4. Read transaction records.
                    5. Load records into staging database tables.
                    6. Match transactions using applicable identifiers,
                       RRN, UPI transaction ID, amount and transaction date.
                    7. Compare transaction statuses.
                    8. Identify matched and unmatched transactions.
                    9. Generate reconciliation reports.
                    10. Investigate and resolve exceptions.
                    
                    10. COMMON UPI RECONCILIATION EXCEPTIONS
                    
                    1. BANK_ONLY:
                       Transaction exists in bank data but not in external data.
                    
                    2. NPCI_ONLY:
                       Transaction exists in external data but not in bank data.
                    
                    3. AMOUNT_MISMATCH:
                       Transaction amount differs between the records.
                    
                    4. STATUS_MISMATCH:
                       Bank status and external status do not agree.
                    
                    5. DUPLICATE_TRANSACTION:
                       Duplicate transaction records are identified.
                    
                    6. REVERSAL_MISMATCH:
                       Expected reversal information is missing or inconsistent.
                    
                    7. PENDING_TRANSACTION:
                       Final transaction status requires further investigation.
                    
                    11. UPI REVERSAL PROCESS
                    
                    Consider this example:
                    
                    Ravi sends Rs. 2,000 to Amit.
                    
                    1. Ravi's bank account is debited.
                    2. Amit's bank account is not credited.
                    3. The transaction is identified as unsuccessful.
                    4. The applicable reversal process is initiated.
                    5. Ravi's account is credited back according to
                       the applicable transaction rules and timelines.
                    
                    12. UPI DISPUTE MANAGEMENT
                    
                    A customer can raise a complaint for issues such as:
                    
                    1. Amount debited but beneficiary not credited.
                    2. Duplicate debit.
                    3. Incorrect transaction status.
                    4. Refund not received.
                    5. Suspected unauthorized transaction.
                    
                    The bank investigates the transaction using reference
                    identifiers and follows the applicable dispute resolution
                    and adjustment process.
                    
                    13. UPI RECONCILIATION EXAMPLE
                    
                    Bank transaction:
                    
                    RRN: 123456789001
                    Amount: Rs. 5,000
                    Status: SUCCESS
                    
                    External transaction:
                    
                    RRN: 123456789001
                    Amount: Rs. 5,000
                    Status: FAILED
                    
                    Reconciliation result:
                    
                    STATUS_MISMATCH
                    
                    Reason:
                    Bank records show SUCCESS, whereas the external record
                    shows FAILED.
                    
                    The operations team must investigate the final status,
                    credit confirmation and applicable reversal details.
                    
                    14. ADVANTAGES OF UPI
                    
                    1. Fast and convenient fund transfers.
                    2. Simple mobile-based payments.
                    3. Interoperability across participating banks.
                    4. Supports merchant and individual payments.
                    5. Reduces dependency on cash transactions.
                    6. Enables digital payment adoption.
                    
                    15. SUMMARY
                    
                    UPI is an important digital payment infrastructure
                    in India.
                    
                    It enables instant payments between participating banks.
                    
                    From a banking technology perspective, UPI reconciliation
                    is important for identifying transaction mismatches,
                    tracking reversals, investigating disputes and verifying
                    applicable settlement records.
                    """
            ,

            "imps", """
                    IMPS - Immediate Payment Service
                    
                    IMPS is an instant interbank electronic fund transfer
                    service in India operated by NPCI.
                    
                    It enables customers to transfer money from one bank
                    account to another bank account in real time.
                    
                    HOW IMPS WORKS:
                    
                    1. Customer initiates an IMPS payment.
                    2. Customer provides beneficiary details.
                    3. Sending bank validates the transaction.
                    4. Payment request is processed through the IMPS network.
                    5. Beneficiary bank processes the credit.
                    6. Transaction response is communicated to the banks.
                    
                    KEY FEATURES:
                    
                    1. Instant interbank fund transfer.
                    2. Available 24x7.
                    3. Supports mobile and internet banking.
                    4. Supports account number and IFSC transfers.
                    5. Provides transaction confirmation.
                    
                    RECONCILIATION:
                    
                    Banks compare internal IMPS transaction records with
                    applicable external transaction and settlement records.
                    
                    Common exceptions:
                    - Debit successful but credit unsuccessful.
                    - Credit successful but debit record missing.
                    - Amount mismatch.
                    - Duplicate transactions.
                    - Reversal not received.
                    """,

            "neft", """
                    NEFT - National Electronic Funds Transfer
                    
                    NEFT is an electronic payment system operated by RBI.
                    
                    It enables customers to transfer money electronically
                    from one bank account to another bank account.
                    
                    Unlike UPI and IMPS, NEFT transactions are processed
                    in half-hourly batches.
                    
                    HOW NEFT WORKS:
                    
                    1. Customer initiates an NEFT transaction.
                    2. Customer provides beneficiary name, account number
                       and IFSC code.
                    3. Sending bank validates the transaction.
                    4. Transaction enters the applicable NEFT batch.
                    5. RBI processes the interbank settlement.
                    6. Beneficiary bank receives the payment information.
                    7. Beneficiary account is credited.
                    
                    KEY FEATURES:
                    
                    1. Available 24x7, including holidays.
                    2. Processed in half-hourly batches.
                    3. Supports interbank fund transfers.
                    4. Uses bank account number and IFSC.
                    5. Suitable for regular fund transfers.
                    
                    RECONCILIATION:
                    
                    Banks compare internal NEFT transaction records with
                    applicable RBI settlement and transaction records.
                    
                    Common exceptions:
                    - Transaction not settled.
                    - Beneficiary credit failure.
                    - Incorrect beneficiary details.
                    - Amount mismatch.
                    - Return or reversal not received.
                    """
    );

    // Search predefined answer using keyword
    public static String getAnswer(String question) {

        if (question == null || question.isBlank()) {
            return null;
        }

        String normalized = question.toLowerCase().trim();

        for (Map.Entry<String, String> entry : ANSWERS.entrySet()) {

            String keyword = entry.getKey();

            if (normalized.matches("(?s).*\\b" + keyword + "\\b.*")) {

                System.out.println("Matched keyword: " + keyword);

                return entry.getValue();
            }
        }

        return null;
    }
}
