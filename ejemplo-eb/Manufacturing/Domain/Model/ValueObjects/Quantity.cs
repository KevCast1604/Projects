namespace ejemplo_eb.Manufacturing.Domain.Model.ValueObjects
{
    public record Quantity
    {
        public int Value { get; }

        public Quantity(int value)
        {
            if (value <= 0)
                throw new ArgumentException("Quantity must be greater than zero.");

            Value = value;
        }

        public override string ToString() => Value.ToString();
    }
}
