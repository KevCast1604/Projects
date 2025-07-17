namespace ejemplo_eb.Manufacturing.Domain.Model.ValueObjects
{
    public record ProductName
    {
        public string Value { get; }

        public ProductName(string value)
        {
            if (string.IsNullOrWhiteSpace(value))
                throw new ArgumentException("Product name cannot be empty.");

            if (value.Length > 100)
                throw new ArgumentException("Product name is too long.");

            Value = value;
        }

        public override string ToString() => Value;
    }
}
