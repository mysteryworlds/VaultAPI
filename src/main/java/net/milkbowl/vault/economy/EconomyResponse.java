/* This file is part of Vault.

    Vault is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Vault is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Vault.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.milkbowl.vault.economy;

import com.google.common.base.Preconditions;

import java.util.Optional;

/**
 * Indicates a typical Return for an Economy method.  
 * It includes a {@link ResponseType} indicating whether the plugin currently being used for Economy actually allows
 * the method, or if the operation was a success or failure.
 */
public class EconomyResponse {

    private static EconomyResponse NOT_IMPLEMENTED_RESPONSE = EconomyResponse.create(-1, -1, ResponseType.NOT_IMPLEMENTED, "Not implemented");

    /**
     * Amount modified by calling method
     */
    @Deprecated
    public final double amount;

    /**
     * New balance of account
     */
    @Deprecated
    public final double balance;
    /**
     * Success or failure of call. Using Enum of ResponseType to determine valid
     * outcomes
     */
    @Deprecated
    public final ResponseType type;

    /**
     * Error message if the variable 'type' is ResponseType.FAILURE
     */
    @Deprecated
    public final String errorMessage;

    /**
     * Constructor for EconomyResponse
     * @param amount Amount modified during operation
     * @param balance New balance of account
     * @param type Success or failure type of the operation
     * @param errorMessage Error message if necessary (commonly null)
     * @deprecated
     */
    @Deprecated
    public EconomyResponse(double amount, double balance, ResponseType type, String errorMessage) {
        this.amount = amount;
        this.balance = balance;
        this.type = type;
        this.errorMessage = errorMessage;
    }

    /**
     * Factory method for a full construction.
     *
     * @param amount Amount modified during operation
     * @param balance New balance of account
     * @param type Success or failure type of the operation
     * @param errorMessage Error message if necessary
     * @return The economy response.
     */
    public static EconomyResponse create(double amount, double balance, ResponseType type, String errorMessage) {
        Preconditions.checkNotNull(type);

        return new EconomyResponse(amount, balance, type, errorMessage);
    }

    /**
     * Obtain a response that indicates that the requested feature is not implemented.
     *
     * @return The economy response.
     */
    public static EconomyResponse notImplemented() {
        return NOT_IMPLEMENTED_RESPONSE;
    }

    /**
     * Create a response for a successful operation.
     *
     * @param amount Amount modified during operation
     * @param balance New balance of account
     * @return The economy response.
     */
    public static EconomyResponse success(double amount, double balance) {
        return EconomyResponse.create(amount, balance, ResponseType.SUCCESS, null);
    }

    /**
     * Create a response for a failed operation.
     *
     * @param amount Amount modified during operation
     * @param balance New balance of account
     * @param errorMessage Error message if necessary
     * @return The economy response.
     */
    public static EconomyResponse failure(double amount, double balance, String errorMessage) {
        return EconomyResponse.create(amount, balance, ResponseType.FAILURE, errorMessage);
    }

    /**
     * Checks if an operation was successful
     * @return Value
     */
    public boolean transactionSuccess() {
        return type == ResponseType.SUCCESS;
    }

    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(errorMessage);
    }

    @Deprecated
    public String getErrorMessageString() {
        return errorMessage;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public ResponseType getType() {
        return type;
    }

    /**
     * Enum for types of Responses indicating the status of a method call.
     */
    public enum ResponseType {
        SUCCESS(1),
        FAILURE(2),
        NOT_IMPLEMENTED(3);

        private int id;

        ResponseType(int id) {
            this.id = id;
        }

        int getId() {
            return id;
        }
    }
}