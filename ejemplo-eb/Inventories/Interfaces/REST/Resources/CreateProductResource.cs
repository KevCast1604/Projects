namespace ejemplo_eb.Inventories.Interfaces.REST.Resources
{
    public record CreateProductResource (
        string Name,
        string ProductTypeCode, // "BTP", "BTS", "MTS", "MTO", "MTA"
        int MaxProductionCapacity
        );
}
