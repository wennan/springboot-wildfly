package com.example.demo.transactions;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final Map<Long, Transaction> transactions = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public Transaction create(TransactionRequest request) {
        OffsetDateTime now = OffsetDateTime.now();
        Transaction transaction = new Transaction(
                idSequence.getAndIncrement(),
                request.getUserId(),
                request.getAmount(),
                request.getType(),
                request.getCategory(),
                request.getNote(),
                request.getTransactionDate(),
                now,
                now);
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    public List<Transaction> findAll(Long userId, TransactionType type, LocalDate fromDate, LocalDate toDate) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (userId != null && !userId.equals(transaction.getUserId())) {
                continue;
            }
            if (type != null && type != transaction.getType()) {
                continue;
            }
            if (fromDate != null && transaction.getTransactionDate().isBefore(fromDate)) {
                continue;
            }
            if (toDate != null && transaction.getTransactionDate().isAfter(toDate)) {
                continue;
            }
            filtered.add(transaction);
        }
        filtered.sort(Comparator.comparing(Transaction::getTransactionDate).reversed()
                .thenComparing(Transaction::getId, Comparator.reverseOrder()));
        return filtered;
    }

    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(transactions.get(id));
    }

    public Optional<Transaction> update(Long id, TransactionRequest request) {
        Transaction existing = transactions.get(id);
        if (existing == null) {
            return Optional.empty();
        }
        existing.setUserId(request.getUserId());
        existing.setAmount(request.getAmount());
        existing.setType(request.getType());
        existing.setCategory(request.getCategory());
        existing.setNote(request.getNote());
        existing.setTransactionDate(request.getTransactionDate());
        existing.setUpdatedAt(OffsetDateTime.now());
        return Optional.of(existing);
    }

    public boolean delete(Long id) {
        return transactions.remove(id) != null;
    }
}
