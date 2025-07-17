using ejemplo_eb.Inventories.Interfaces.REST.Resources;
using EjemploEb.Inventories.Domain.Model.Aggregates;

namespace ejemplo_eb.Inventories.Interfaces.REST.Transform
{
    public static class ProductResourceFromEntityAssembler
    {
        public static ProductResource FromEntity(Product product)
        {
            return new ProductResource(
                Id: product.Id,
                ProductNumber: product.ProductNumber.ToString(), // UUID a string
                Name: product.Name,
                ProductType: product.ProductType.ToString(), // Enum a nombre string
                CurrentProductionQuantity: product.CurrentProductionQuantity,
                MaxProductionCapacity: product.MaxProductionCapacity
            );
        }
    }
}
