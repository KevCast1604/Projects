using ejemplo_eb.Inventories.Domain.Model.Commands;
using ejemplo_eb.Inventories.Interfaces.REST.Resources;

namespace ejemplo_eb.Inventories.Interfaces.REST.Transform
{
    public static class CreateProductCommandFromResourceAssembler
    {
        public static CreateProductCommand ToCommand(CreateProductResource resource)
        {
            return new CreateProductCommand(
                Name: resource.Name,
                ProductTypeCode: resource.ProductTypeCode,
                MaxProductionCapacity: resource.MaxProductionCapacity
            );
        }
    }
}
