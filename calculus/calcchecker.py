print("Hello, world!")

# print("Begin Sympy")
import sympy as sp
x = sp.Symbol('x')
# print(limit(sin(x)/x,x,0))
# print(integrate(1/x,x))
f = sp.exp(x * sp.exp(x)) * (sp.exp(x) + x * sp.exp(x))
res = sp.integrate(f, x)
sp.pprint(res)

