using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using ejemplo_eb.Shared.Domain.Aggregates;

namespace EjemploEb.Inventories.Domain.Model.Aggregates
{
    public enum EProductType
    {
        BuildToPrint = 0,
        BuildToSpecification = 1,
        MadeToStock = 2,
        MadeToOrder = 3,
        MadeToAssemble = 4
    }

    [Table("products")]
    public class Product : AuditableModel
    {
        [Key]
        [Column("id")]
        public int Id { get; private set; }

        [Required]
        [Column("product_number")]
        public Guid ProductNumber { get; private set; } // UUID

        [Required]
        [MaxLength(60)]
        [Column("name")]
        public string Name { get; private set; }

        [Required]
        [Column("product_type")]
        public EProductType ProductType { get; private set; }

        [Required]
        [Column("current_production_quantity")]
        public int CurrentProductionQuantity { get; private set; }

        [Required]
        [Column("max_production_capacity")]
        public int MaxProductionCapacity { get; private set; }

        protected Product() { }

        public Product(string name, EProductType productType, int maxProductionCapacity)
        {
            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("Name is required");

            if (name.Length > 60)
                throw new ArgumentException("Name must be 60 characters max");

            ProductNumber = Guid.NewGuid();
            Name = name;
            ProductType = productType;
            MaxProductionCapacity = maxProductionCapacity;
            CurrentProductionQuantity = 0;
        }

        public void IncrementCurrentProductionQuantity(int quantity)
        {
            if (quantity <= 0)
                throw new ArgumentException("Quantity must be positive");

            if (CurrentProductionQuantity + quantity > MaxProductionCapacity)
                throw new InvalidOperationException("Exceeds max production capacity");

            CurrentProductionQuantity += quantity;
        }
    }
}
