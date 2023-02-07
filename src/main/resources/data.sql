-- payment_methods table
INSERT INTO payment_methods (name, display_name, payment_type)
VALUES
    ('credit card', 'credit card', 'CREDIT_CARD'),
    ('alfa_lb', 'Alfa Lebanon', 'MOBILE_CARRIER'),
    ('voucher', 'Voucher', 'VOUCHER');

-- payment_plans table
INSERT INTO payment_plans (payment_plan_id, net_amount, tax_amount, gross_amount, currency, duration)
VALUES
    ( 1,5.99, 0, 5.99, 'USD', 'Month'),
    (72, 5.99, 0, 5.99, 'USD', 'Month'),
    ( 54, 10, 0, 10, 'SAR', 'Week');
