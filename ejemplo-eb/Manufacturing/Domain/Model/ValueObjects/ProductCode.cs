namespace ejemplo_eb.Manufacturing.Domain.Model.ValueObjects
{
    public record ProductCode
    {
        public string Value { get; }

        public ProductCode(string value)
        {
            if (string.IsNullOrWhiteSpace(value))
                throw new ArgumentException("Product code cannot be empty.");

            if (!System.Text.RegularExpressions.Regex.IsMatch(value, @"^[A-Z0-9\-]+$"))
                throw new ArgumentException("Product code must be alphanumeric and may include dashes.");

            Value = value;
        }

        public override string ToString() => Value;
    }
}
