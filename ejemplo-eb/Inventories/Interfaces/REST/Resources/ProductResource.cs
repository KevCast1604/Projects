namespace ejemplo_eb.Inventories.Interfaces.REST.Resources
{
    public record ProductResource(
        int Id,
        string ProductNumber,  // UUID string
        string Name,
        string ProductType,    // Nombre completo: "BuildToPrint", "MadeToOrder", etc.
        int CurrentProductionQuantity,
        int MaxProductionCapacity
    );
}
