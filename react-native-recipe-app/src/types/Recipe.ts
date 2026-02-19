export interface Recipe {
  id: number;
  name: string;
  category: string;
  description: string;
  prepTime: string;
  cookTime: string;
  servings: number;
  difficulty: string;
  ingredients: string[];
  instructions: string[];
  image: string;
}
