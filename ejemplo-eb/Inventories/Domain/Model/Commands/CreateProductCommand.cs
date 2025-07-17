namespace ejemplo_eb.Inventories.Domain.Model.Commands
{
    public record CreateProductCommand(
       string Name,
       string ProductTypeCode, // "BTP", "BTS", "MTS", "MTO", "MTA"
       int MaxProductionCapacity
   );
}
